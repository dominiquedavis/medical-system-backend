package com.medicalsystem.importer;

import com.medicalsystem.model.FormType;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class ExcelSpreadsheetDataImporter implements DataImporter {

    private final RowImporter rowImporter;

    // For testing purposes: set -1 to import all rows
    private final int ROWS_TO_IMPORT = 5;

    /**
     * Imports given excel file to the database
     *
     * @param excelFile a FileInputStream object representing a .xlsx file
     */
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

    /**
     * Loads an excel file into memory
     *
     * @param excelFile a FileInputStream object representing a .xlsx file
     * @return          a Workbook object
     */
    private Workbook loadWorkbook(FileInputStream excelFile) {
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(excelFile);
        } catch (IOException e) {
            log.severe(e.getMessage());
        }
        return workbook;
    }

    /**
     * Imports the sheet with OPEN operations
     *
     * @param openSheet a Sheet object
     */
    private void importOpenSheet(Sheet openSheet) {
        importSheet(openSheet, FormType.OPEN);
    }

    /**
     * Imports the sheet with EVAR operations
     *
     * @param evarSheet a Sheet object
     */
    private void importEvarSheet(Sheet evarSheet) {
        importSheet(evarSheet, FormType.EVAR);
    }

    /**
     * Imports any sheet object
     *
     * @param sheet    a Sheet object
     * @param formType either OPEN or EVAR
     */
    private void importSheet(Sheet sheet, FormType formType) {
        Iterator<Row> iterator = sheet.rowIterator();

        // Skip headers
        if (sheet.getPhysicalNumberOfRows() < 3) {
            log.info("Open sheet contains no data rows");
            return;
        }

        iterator.next();

        Row header = iterator.next();
        int maxNumberOfCells = header.getLastCellNum();

        // TESTING PURPOSES
        if (ROWS_TO_IMPORT != -1) {
            int i = 0;
            while (i < ROWS_TO_IMPORT && iterator.hasNext()) {
                rowImporter.importRow(iterator.next(), formType, maxNumberOfCells);
                i++;
            }
            return;
        }
        //

        // Process the rest of the rows
        iterator.forEachRemaining(row -> rowImporter.importRow(row, formType, maxNumberOfCells));

    }

}
