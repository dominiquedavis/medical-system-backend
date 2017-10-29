package com.medicalsystem.excel.importer.impl;

import com.medicalsystem.excel.importer.ExcelImporter;
import com.medicalsystem.excel.importer.SheetImporter;
import com.medicalsystem.exception.ExcelImportException;
import com.medicalsystem.util.ExcelUtils;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class ExcelImporterImpl implements ExcelImporter {

    private final SheetImporter sheetImporter;

    @Override
    public void importToDatabase(String excelFileName) {
        importHelper(excelFileName, -1);
    }

    @Override
    public void importToDatabase(String excelFileName, int rowsToImport) {
        importHelper(excelFileName, rowsToImport);
    }

    private void importHelper(String excelFileName, int rowsToImport) {
        Workbook workbook;
        try {
            workbook = ExcelUtils.loadWorkbook(excelFileName);
        } catch (IOException e) {
            log.severe(e.getMessage());
            log.severe("File not imported: " + excelFileName);
            return;
        }

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            try {
                if (rowsToImport == -1) {
                    sheetImporter.importToDatabase(sheet, i);
                } else {
                    sheetImporter.importToDatabase(sheet, i, rowsToImport);
                }
            } catch (ExcelImportException e) {
                log.severe(e.getMessage());
                log.severe("Sheet not imported: " + i);
            }
        }

        log.info("Import completed");
    }

}
