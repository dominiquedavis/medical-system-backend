package com.medicalsystem.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;

public class CellUtils {

    private static final DataFormatter formatter = new DataFormatter();

    public static String getStringValue(Cell cell) {
        return formatter.formatCellValue(cell);
    }

}
