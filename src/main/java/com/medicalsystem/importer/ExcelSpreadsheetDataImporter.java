package com.medicalsystem.importer;

import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;

@Component
@Log
public class ExcelSpreadsheetDataImporter implements DataImporter {

    @Override
    public void importToDatabase(FileInputStream excelFile) {
        Workbook workbook = loadWorkbook(excelFile);

        if (workbook == null) {
            log.severe("Error loading excel file");
            return;
        }

        int numberOfSheets = workbook.getNumberOfSheets();

        if (numberOfSheets >= 2) {
            importOpenSheet(workbook.getSheetAt(0));
            importEvarSheet(workbook.getSheetAt(1));
        }

        else if (numberOfSheets == 1) {
            importOpenSheet(workbook.getSheetAt(0));
        }

        else {
            log.severe("No sheets found");
        }
    }

    private Workbook loadWorkbook(FileInputStream excelFile) {
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(excelFile);
        } catch (IOException e) {
            log.severe(e.getMessage());
        }
        return workbook;
    }

    private void importOpenSheet(Sheet openSheet) {

    }

    private void importEvarSheet(Sheet evarSheet) {

    }

    private void importSheet(Sheet sheet) {

    }

}
