package com.medicalsystem.excel.exporter

import com.medicalsystem.domain.template.Form
import com.medicalsystem.service.FieldService
import com.medicalsystem.service.FormService
import com.medicalsystem.util.logger
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Component
import java.io.FileOutputStream

@Component
class TemplateExporter(private val formService: FormService, private val fieldService: FieldService) {

    fun exportTemplate(path: String) {
        val workbook: Workbook = XSSFWorkbook()

        formService.findAll()
                .sortedBy { it.sheetName }
                .reversed()
                .forEach { prepareHeaders(it, workbook.createSheet(it.sheetName)) }

        saveWorkbook(workbook, path)

        logger().info("Export completed: '$path'")
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
                .forEach { secondHeader.createCell(it.columnIdx).setCellValue(it.name) }
    }

    private fun saveWorkbook(workbook: Workbook, path: String) =
            FileOutputStream(path).use { workbook.write(it) }
}