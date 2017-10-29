package com.medicalsystem.excel.exporter;

import java.io.IOException;

public interface ExcelExporter {

    void exportToFile(String excelFilePath) throws IOException;

}
