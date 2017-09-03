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

import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;

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
                    saveValue(cell, patientId, columnIndexes, TextFieldValue::new, cell.getStringCellValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    saveNumericValue(cell, patientId, columnIndexes);
                    break;
            }
        }

        log.info("ROW PERSISTED: " + row.getRowNum());
    }

    private <T> void  saveValue(Cell cell, int patientId, Map<Integer, String> columnIndexes, Supplier supplier,
                                   T value) {
        FieldValue<T> field = (FieldValue<T>) supplier.get();
        field.setPatientId(patientId);
        Field fieldByName = fieldService.findByName(columnIndexes.get(cell.getColumnIndex()));
        if (fieldByName == null) {
            return;
        }
        field.setField(fieldByName);
        field.setValue(value);
        fieldValueService.saveOrUpdate(field);

    }
    private void saveNumericValue(Cell cell, int patientId, Map<Integer, String> columnIndexes) {

        if (DateUtil.isCellDateFormatted(cell)) {
            saveValue(cell, patientId, columnIndexes, DateFieldValue::new, cell.getDateCellValue());
        } else {
            Double numericCellValue = cell.getNumericCellValue();
            if(Math.floor(numericCellValue) == numericCellValue){
                saveValue(cell, patientId, columnIndexes, IntegerFieldValue::new, (int) cell.getNumericCellValue());
            } else {
                saveValue(cell, patientId, columnIndexes, DoubleFieldValue::new, cell.getNumericCellValue());
            }
        }
    }
}
