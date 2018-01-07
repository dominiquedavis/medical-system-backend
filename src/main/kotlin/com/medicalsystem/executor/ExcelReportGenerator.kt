package com.medicalsystem.executor

import com.medicalsystem.domain.Patient
import com.medicalsystem.domain.report.ExcelReportInfo
import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.template.Form
import com.medicalsystem.domain.value.FieldValue
import com.medicalsystem.exception.NO_FIELD_VALUE_FOR_FIELD_AND_PATIENT
import com.medicalsystem.exception.NO_FIELD_WITH_ID
import com.medicalsystem.service.FieldService
import com.medicalsystem.service.FieldValueService
import com.medicalsystem.util.logger
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Component
import java.io.FileOutputStream
import javax.persistence.EntityNotFoundException

@Component
class ExcelReportGenerator(private val fieldService: FieldService, private val fieldValueService: FieldValueService) {
    var excelReportInfo: ExcelReportInfo? = null

    fun generateExcelFile(path: String) {
        if (excelReportInfo == null) {
            throw IllegalStateException("Wykonaj raport przed pobraniem wyników.")
        }

        val reportInfo = excelReportInfo!!
        val forms = reportInfo.formsToPatientsMeetingCriteria.keys
        val fields: List<Field> = reportInfo.includedReportFields.map { fieldService.findByID(it.formField) ?:
                throw EntityNotFoundException(NO_FIELD_WITH_ID + it.formField) }
                .sortedBy { it.columnIdx }

        val workbook: Workbook = XSSFWorkbook()

        forms.sortedBy { it.sheetName }.reversed()
                .forEach { exportForm(it, workbook.createSheet(it.sheetName), fields,
                        reportInfo.formsToPatientsMeetingCriteria[it] ?: throw RuntimeException("key not found: $it")) }

        saveWorkbook(workbook, path)

        excelReportInfo = null
        logger().info("Export completed: '$path'")
    }

    private fun exportForm(form: Form, sheet: Sheet, fields: List<Field>, patients: List<Patient>) {
        prepareHeaders(form, sheet, fields)
        exportData(form, sheet, fields, patients)
    }

    private fun prepareHeaders(form: Form, sheet: Sheet, fields: List<Field>) {
        // TODO: Create first header row
        val firstHeader: Row = sheet.createRow(0)

        // Create second header row
        val secondHeader: Row = sheet.createRow(1)

        // Create header for ID column
        secondHeader.createCell(0).setCellValue("ID")

        // Create headers for the rest of the fields
        //fields.forEach { secondHeader.createCell(it.columnIdx).setCellValue(it.name) }
        fields.forEachIndexed { index, field ->
            secondHeader.createCell(index + 1).setCellValue(field.name)
        }
    }

    private fun exportData(form: Form, sheet: Sheet, fields: List<Field>, patients: List<Patient>) {
        var rowIndex = 2
        patients.sortedBy { it.id }.forEach { exportPatient(it, sheet.createRow(rowIndex++), fields) }
    }

    private fun exportPatient(patient: Patient, row: Row, fields: List<Field>) {
        row.createCell(0).setCellValue(patient.id)
        fields.forEachIndexed { index, field ->
            val fieldValue: FieldValue<*> = fieldValueService.findByFieldAndPatientByNameNoCreate(field.name, patient) ?:
                    throw EntityNotFoundException(NO_FIELD_VALUE_FOR_FIELD_AND_PATIENT + "$field $patient")
            fieldValue.exportToExcelCell(row, index + 1)
        }
    }

    private fun saveWorkbook(workbook: Workbook, path: String) =
            FileOutputStream(path).use { workbook.write(it) }
}