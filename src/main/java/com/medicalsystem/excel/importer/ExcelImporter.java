package com.medicalsystem.excel.importer;

import java.io.FileInputStream;

public interface ExcelImporter {

    void importToDatabase(FileInputStream excelFileStream);

    void importToDatabase(FileInputStream excelFileStream, int rowsToImport);

}
