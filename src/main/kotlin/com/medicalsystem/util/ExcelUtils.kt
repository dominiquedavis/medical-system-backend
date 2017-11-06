package com.medicalsystem.util

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream
import java.io.IOException

object ExcelUtils {
    private val formatter = DataFormatter()

    fun loadWorkbook(path: String): Workbook? =
            try {
                XSSFWorkbook(FileInputStream(path))
            } catch (e: IOException) {
                logger().error(e)
                null
            }

    fun asString(cell: Cell): String = formatter.formatCellValue(cell)
}