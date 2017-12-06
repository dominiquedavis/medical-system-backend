package com.medicalsystem.util

import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream

object ExcelUtils {

    fun loadWorkbook(excelFilePath: String): Workbook =
                XSSFWorkbook(FileInputStream(excelFilePath))

}