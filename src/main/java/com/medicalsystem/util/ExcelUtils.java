package com.medicalsystem.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public final class ExcelUtils {

    private static final DataFormatter formatter = new DataFormatter();

    private ExcelUtils() {}

    public static Workbook loadWorkbook(FileInputStream excelFileStream) throws IOException {
        return new XSSFWorkbook(excelFileStream);
    }

    public static String getValueAsString(Cell cell) {
        return formatter.formatCellValue(cell);
    }
}
