package com.medicalsystem.excel.importer.impl;

import com.medicalsystem.excel.importer.RowImporter;
import com.medicalsystem.excel.importer.SheetImporter;
import com.medicalsystem.exception.ExcelImportException;
import com.medicalsystem.model.Field;
import com.medicalsystem.model.Form;
import com.medicalsystem.service.FieldService;
import com.medicalsystem.service.FormService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class SheetImporterImpl implements SheetImporter {

    private final int NUMBER_OF_HEADER_ROWS = 2;

    private final FormService formService;
    private final FieldService fieldService;
    private final RowImporter rowImporter;

    @Override
    public void importToDatabase(Sheet sheet, int sheetIndex) throws ExcelImportException {
        importHelper(sheet, sheetIndex, -1);
    }

    @Override
    public void importToDatabase(Sheet sheet, int sheetIndex, int rowsToImport) throws ExcelImportException {
        importHelper(sheet, sheetIndex, rowsToImport);
    }

    private void importHelper(Sheet sheet, int sheetIndex, int rowsToImport) throws ExcelImportException {
        Form form = formService.getByExcelSheetIndex(sheetIndex);

        if (form == null) {
            throw new ExcelImportException("Form not found with the given sheet index: " + sheetIndex);
        }

        if (sheet.getPhysicalNumberOfRows() <= NUMBER_OF_HEADER_ROWS) {
            log.warning("Sheet contains no data rows: " + sheetIndex);
            return;
        }

        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next();

        int maxNumberOfCells = rowIterator.next().getLastCellNum();
        Map<Integer, Field> fields = fieldService.getExcelColumnIndexToFieldMap(form);

        if (rowsToImport == -1) {
            while (rowIterator.hasNext()) {
                rowImporter.importToDatabase(rowIterator.next(), maxNumberOfCells, form, fields);
            }
        } else {
            for (int i = 0; i < rowsToImport && rowIterator.hasNext(); i++) {
                rowImporter.importToDatabase(rowIterator.next(), maxNumberOfCells, form, fields);
            }
        }
    }
}
