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
            String fieldName = columnIndexes.get(cell.getColumnIndex());
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    saveNumericValue(fieldName, patientId, cell);
                    break;
                case Cell.CELL_TYPE_STRING:
                    saveValue(fieldName, patientId, TextFieldValue::new, cell.getStringCellValue());
                    break;
            }
        }

        log.info("ROW PERSISTED: " + row.getRowNum());
    }

    private void saveNumericValue(String fieldName, int patientId, Cell cell) {
        if (DateUtil.isCellDateFormatted(cell)) {
            saveValue(fieldName, patientId, DateFieldValue::new, cell.getDateCellValue());
        } else {
            Double numericCellValue = cell.getNumericCellValue();
            if(Math.floor(numericCellValue) == numericCellValue){
                saveValue(fieldName, patientId, IntegerFieldValue::new, (int) cell.getNumericCellValue());
            } else {
                saveValue(fieldName, patientId, DoubleFieldValue::new, cell.getNumericCellValue());
            }
        }
    }

    private <S, T extends FieldValue<S>> void  saveValue(String fieldName, int patientId, Supplier<T> supplier, S value) {
        T field = supplier.get();
        field.setPatientId(patientId);
        Field fieldByName = fieldService.findByName(fieldName);
        if (fieldByName == null) {
            return;
        }
        field.setField(fieldByName);
        field.setValue(value);
        fieldValueService.saveOrUpdate(field);

    }

}
