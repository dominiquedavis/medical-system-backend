package com.medicalsystem.excel.importer;

import java.io.FileInputStream;

public interface ExcelImporter {

    void importToDatabase(String excelFileName);

    void importToDatabase(String excelFileName, int rowsToImport);

}
