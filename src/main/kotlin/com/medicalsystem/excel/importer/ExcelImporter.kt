package com.medicalsystem.excel.importer

import com.medicalsystem.excel.result.ImportResult
import com.medicalsystem.util.ExcelUtils
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ExcelImporter(private val sheetImporter: SheetImporter) {

    @Transactional
    fun importToDatabase(excelFilePath: String): ImportResult {
        val workbook = ExcelUtils.loadWorkbook(excelFilePath)
        val result = ImportResult()

        (0 until workbook.numberOfSheets)
                .map { workbook.getSheetAt(it) }
                .forEach {
                    sheetImporter.importToDatabase(it, result)
                }

        return result
    }
}