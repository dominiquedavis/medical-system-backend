package com.medicalsystem.excel.importer;

import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
@Log
public class Importer {

    private XSSFSheet openSheet;
    private XSSFSheet evarSheet;

    private final RowImporter rowImporter;

    @Autowired
    public Importer(RowImporter rowImporter) throws IOException {
        this.rowImporter = rowImporter;
    }

    /**
     * Imports data from the spreadsheet.
     */
    public void importToDB(String fileName) {
        log.info("IMPORTING DATA...");

        init(fileName);

        importOpenSheet();
        importEvarSheet();

        log.info("IMPORT COMPLETE");
    }

    private void init(String fileName) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(fileName)));
            this.openSheet = workbook.getSheetAt(0);
            this.evarSheet = workbook.getSheetAt(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Imports data from the 'open' sheet.
     */
    private void importOpenSheet() {
        /* Otwarte */
        Iterator<Row> rowIterator = openSheet.iterator();

        /* Skip headers */
        rowIterator.next();
        Map<Integer, String> columnIndexes = saveColumnIndexes(rowIterator.next());

        int rows = 0;

        /* Iterate over rows and put each to the DB */
        while (rowIterator.hasNext()) {
            if (rows++ > 6)
                break;
            Row row = rowIterator.next();
            rowImporter.importToDB(row, columnIndexes);
        }
    }

    private Map<Integer, String> saveColumnIndexes(Row row) {
        Map<Integer, String> columnIndexes = new HashMap<>();
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            columnIndexes.put(cell.getColumnIndex(), cell.getStringCellValue());
        }
        return columnIndexes;
    }

    /**
     * Imports data from the 'EVAR' sheet.
     */
    private void importEvarSheet() {
        // TODO: Implement
    }

}
