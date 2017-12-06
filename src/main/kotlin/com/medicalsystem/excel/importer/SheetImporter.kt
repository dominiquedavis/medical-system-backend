package com.medicalsystem.excel.importer

import com.medicalsystem.excel.result.ImportResult
import org.apache.poi.ss.usermodel.Sheet
import org.springframework.stereotype.Component

@Component
class SheetImporter(private val sheetConverter: SheetConverter,
                    private val rowImporter: RowImporter) {

    fun importToDatabase(_sheet: Sheet, result: ImportResult) {
        val sheet: MySheet = sheetConverter.convert(_sheet)

        if (!sheet.containsDataRows) {
            result.noDataRowsInSheet(sheet)
            return
        }

        sheet.rowIterator().asSequence()
                .drop(sheet.numberOfHeaderRows)
                .forEach { rowImporter.importToDatabase(it, sheet, result) }
    }
}