package com.medicalsystem.util;

import org.apache.poi.ss.usermodel.Cell;

public class CellUtils {

    public static String getStringValue(Cell cell) {
        cell.setCellType(Cell.CELL_TYPE_STRING);
        return cell.getStringCellValue();
    }

}
