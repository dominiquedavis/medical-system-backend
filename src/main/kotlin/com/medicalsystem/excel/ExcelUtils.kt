package com.medicalsystem.excel

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.Workbook

private val formatter = DataFormatter()
private var dateCellStyle: CellStyle? = null

/**
 * Returns cells content as string.
 */
fun Cell.getAsString(): String = formatter.formatCellValue(this).trim()

/**
 * Makes the cell display dates properly.
 */
fun Cell.setStyleAsDate() {
    if (dateCellStyle == null) {
        val workbook: Workbook = this.row.sheet.workbook
        dateCellStyle = workbook.createCellStyle()
        dateCellStyle!!.dataFormat = workbook.creationHelper.createDataFormat().getFormat("dd.mm.yyyy")
    }
    this.cellStyle = dateCellStyle!!
}