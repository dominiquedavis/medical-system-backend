package com.medicalsystem.excel.importer;

import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.DoubleFieldValue;
import com.medicalsystem.model.value.FieldValue;
import com.medicalsystem.model.value.TextFieldValue;
import com.medicalsystem.service.FieldService;
import com.medicalsystem.service.FieldValueService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.apache.poi.ss.usermodel.Row;

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
            if (cell.getCellType() == 0) {
                saveNumericValue(cell, patientId, columnIndexes);
            } else {
                saveStringValue(cell, patientId, columnIndexes);
            }
        }

        log.info("ROW PERSISTED: " + row.getRowNum());
    }

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
