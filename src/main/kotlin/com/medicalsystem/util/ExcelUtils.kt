package com.medicalsystem.util

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream
import java.io.IOException

object ExcelUtils {
    private val formatter = DataFormatter()
    private var dateCellStyle: CellStyle? = null

    fun loadWorkbook(path: String): Workbook? =
            try {
                XSSFWorkbook(FileInputStream(path))
            } catch (e: IOException) {
                logger().error(e)
                null
            }

    fun asString(cell: Cell): String = formatter.formatCellValue(cell)

    fun setDateCellStyle(cell: Cell) {
        if (dateCellStyle == null) {
            val workbook: Workbook = cell.row.sheet.workbook
            dateCellStyle = workbook.createCellStyle()
            dateCellStyle!!.dataFormat = workbook.creationHelper.createDataFormat().getFormat("dd.mm.yyyy")
        }
        cell.cellStyle = dateCellStyle!!
    }
}