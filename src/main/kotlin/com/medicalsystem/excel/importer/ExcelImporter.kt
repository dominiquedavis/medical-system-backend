package com.medicalsystem.excel.importer

import com.medicalsystem.excel.importer.result.ImportError
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.io.FileInputStream

@Component
class ExcelImporter(private val sheetImporter: SheetImporter) {

    /**
     * Imports data from the given excel file and returns result info in a ImportResult aggregate.
     *
     * @param excelFilePath path to the excel file
     * @return import result
     */
    @Transactional
    fun importToDatabase(excelFilePath: String): List<ImportError> {
        val workbook: Workbook = XSSFWorkbook(FileInputStream(excelFilePath))

        // Iterate over sheets and import each of them, aggregating ImportResults
        return (0 until workbook.numberOfSheets)
                .map { workbook.getSheetAt(it) }
                .flatMap { sheetImporter.importSheet(it) }
    }
}