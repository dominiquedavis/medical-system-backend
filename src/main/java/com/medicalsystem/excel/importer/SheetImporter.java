package com.medicalsystem.excel.importer;

import com.medicalsystem.exception.ExcelImportException;
import org.apache.poi.ss.usermodel.Sheet;

public interface SheetImporter {

    void importToDatabase(Sheet sheet, int sheetIndex) throws ExcelImportException;

    void importToDatabase(Sheet sheet, int sheetIndex, int rowsToImport) throws ExcelImportException;

}
