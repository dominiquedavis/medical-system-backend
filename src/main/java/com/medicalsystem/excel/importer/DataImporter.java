package com.medicalsystem.excel.importer;

import java.io.FileInputStream;

public interface DataImporter {

    void importToDatabase(FileInputStream fileInputStream);

}
