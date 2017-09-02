package com.medicalsystem.excel.importer;

import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.*;
import com.medicalsystem.service.FieldService;
import com.medicalsystem.service.FieldValueService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.apache.poi.ss.usermodel.Row;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class RowImporter {

    private final FieldService fieldService;
    private final FieldValueService fieldValueService;

    public void importToDB(Row row, Map<Integer, String> columnIndexes) {

        int patientId = (int) row.getCell(0).getNumericCellValue();
        Iterator<Cell> cellIterator = row.cellIterator();
        cellIterator.next();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    saveStringValue(cell, patientId, columnIndexes);
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    saveNumericValue(cell, patientId, columnIndexes);
                    break;
            }
        }

        log.info("ROW PERSISTED: " + row.getRowNum());
    }



    //TO-DO remove the repeating code

    private void saveStringValue(Cell cell, int patientId, Map<Integer, String> columnIndexes) {
        FieldValue<String> field = new TextFieldValue();
        field.setPatientId(patientId);
        Field fieldByName = fieldService.findByName(columnIndexes.get(cell.getColumnIndex()));
        if (fieldByName == null) {
            return;
        }
        field.setField(fieldByName);
        field.setValue(cell.getStringCellValue());
        fieldValueService.saveOrUpdate(field);
    }

    private void saveNumericValue(Cell cell, int patientId, Map<Integer, String> columnIndexes) {

        if (DateUtil.isCellDateFormatted(cell)) {
            saveDateValue(cell, patientId, columnIndexes);
        } else {
            Double numericCellValue = cell.getNumericCellValue();
            if(Math.floor(numericCellValue) == numericCellValue){
                saveIntegerValue(cell, patientId, columnIndexes);
            } else {
                saveDoubleValue(cell, patientId, columnIndexes);
            }
        }
    }

    private void saveIntegerValue(Cell cell, int patientId, Map<Integer, String> columnIndexes) {
        FieldValue<Integer> field = new IntegerFieldValue();
        field.setPatientId(patientId);
        Field fieldByName = fieldService.findByName(columnIndexes.get(cell.getColumnIndex()));
        if (fieldByName == null) {
            return;
        }
        field.setField(fieldByName);
        field.setValue((int) cell.getNumericCellValue());
        fieldValueService.saveOrUpdate(field);
    }

    //TO-DO create a date parser
    private void saveDateValue(Cell cell, int patientId, Map<Integer, String> columnIndexes) {
        FieldValue<Date> field = new DateFieldValue();
        field.setPatientId(patientId);
        Field fieldByName = fieldService.findByName(columnIndexes.get(cell.getColumnIndex()));
        if (fieldByName == null) {
            return;
        }
        field.setField(fieldByName);
        field.setValue(cell.getDateCellValue());
        fieldValueService.saveOrUpdate(field);

    }

    private void saveDoubleValue(Cell cell, int patientId, Map<Integer, String> columnIndexes) {
        FieldValue<Double> field = new DoubleFieldValue();
        field.setPatientId(patientId);
        Field fieldByName = fieldService.findByName(columnIndexes.get(cell.getColumnIndex()));
        if (fieldByName == null) {
            return;
        }
        field.setField(fieldByName);
        field.setValue(cell.getNumericCellValue());
        fieldValueService.saveOrUpdate(field);
    }
}
