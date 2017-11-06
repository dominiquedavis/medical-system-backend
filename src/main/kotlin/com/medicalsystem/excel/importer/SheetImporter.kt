package com.medicalsystem.excel.importer

import com.medicalsystem.model.Field
import com.medicalsystem.model.Form
import com.medicalsystem.service.FieldService
import com.medicalsystem.service.FormService
import com.medicalsystem.util.logger
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SheetImporter @Autowired constructor(
        val rowImporter: RowImporter,
        val formService: FormService,
        val fieldService: FieldService) {

    val numberOfHeaderRows = 2

    fun importToDatabase(sheet: Sheet) {
        val sheetIndex: Int = sheet.workbook.getSheetIndex(sheet)
        val form: Form = formService.getBySheetIndex(sheetIndex) ?:
                throw ExcelImportException("No form with sheet index: $sheetIndex")

        if (sheet.physicalNumberOfRows <= numberOfHeaderRows) {
            logger().warn("Sheet contains no data rows: ${sheet.sheetName}")
            return
        }

        val fields: Map<Int, Field> = fieldService.getColumnIndexToFieldMap(form)

        sheet.rowIterator()
                .asSequence()
                .drop(numberOfHeaderRows)
                .forEach {
                    try {
                        rowImporter.importToDatabase(it, form, fields, getMaxNumberOfCells(sheet))
                    }  catch (e: ExcelImportException) {
                        logger().error(e)
                        logger().warn("Skipping row ${it.sheet.indexOf(it)}")
                    }
                }
    }

    private fun getMaxNumberOfCells(sheet: Sheet): Int {
        val it: Iterator<Row> = sheet.rowIterator()
        it.next()
        return it.next().lastCellNum.toInt()
    }
}
