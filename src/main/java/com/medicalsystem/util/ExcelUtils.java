package com.medicalsystem.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public final class ExcelUtils {

    private static final DataFormatter formatter = new DataFormatter();
    private static CellStyle dateCellStyle = null;

    private ExcelUtils() {}

    public static Workbook loadWorkbook(String excelFileName) throws IOException {
        return new XSSFWorkbook(new FileInputStream(excelFileName));
    }

    public static String getValueAsString(Cell cell) {
        return formatter.formatCellValue(cell);
    }

    public static void setDateCellStyle(Cell cell) {
        if (dateCellStyle == null) {
            Workbook workbook = cell.getRow().getSheet().getWorkbook();
            dateCellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd.mm.yyyy"));
        }
        cell.setCellStyle(dateCellStyle);
    }
}
