package com.medicalsystem.excel.exporter

import com.medicalsystem.model.Form
import com.medicalsystem.model.Patient
import com.medicalsystem.service.FieldValueService
import com.medicalsystem.service.FormService
import com.medicalsystem.service.PatientService
import com.medicalsystem.util.logger
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.FileOutputStream

@Component
class ExcelExporter @Autowired constructor(
        val formService: FormService,
        val patientService: PatientService,
        val fieldValueService: FieldValueService) {

    fun exportToFile(path: String) {
        val workbook: Workbook = XSSFWorkbook()

        formService.getAll()
                .sortedBy { it.sheetIndex }
                .forEach { exportForm(it, workbook.createSheet(it.name)) }

        saveWorkbook(workbook, path)

        logger().info("Export completed: '$path'")
    }

    private fun exportForm(form: Form, sheet: Sheet) {
        prepareHeaders(form, sheet)
        exportData(form, sheet)
    }

    private fun prepareHeaders(form: Form, sheet: Sheet) {
        // TODO: Create first header row
        val firstHeader: Row = sheet.createRow(0)

        // Create second header row
        val secondHeader: Row = sheet.createRow(1)

        // Create header for ID column
        secondHeader.createCell(0).setCellValue("ID")

        // Create headers for the rest of the fields
        form.sections
                .flatMap { it.fields }
                .forEach { secondHeader.createCell(it.columnIndex).setCellValue(it.name) }
    }

    private fun exportData(form: Form, sheet: Sheet) {
        val patients: List<Patient> = patientService.getAllByForm(form).sortedWith(compareBy { it.id })
        var rowIndex = 2
        patients.forEach { exportPatient(it, sheet.createRow(rowIndex++)) }
    }

    private fun exportPatient(patient: Patient, row: Row) {
        row.createCell(0).setCellValue(patient.id)
        fieldValueService.getAllFieldValuesForPatient(patient).forEach { it.createCell(row) }
    }

    private fun saveWorkbook(workbook: Workbook, path: String) =
            FileOutputStream(path).use { workbook.write(it) }

}