package com.medicalsystem.excel.importer.impl;

import com.medicalsystem.excel.importer.CellImporter;
import com.medicalsystem.exception.ExcelImportException;
import com.medicalsystem.factory.FieldValueFactory;
import com.medicalsystem.model.Field;
import com.medicalsystem.model.Patient;
import com.medicalsystem.model.fieldvalue.FieldValue;
import com.medicalsystem.service.FieldValueService;
import com.medicalsystem.util.ExcelUtils;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class CellImporterImpl implements CellImporter {

    private final FieldValueFactory fieldValueFactory;
    private final FieldValueService fieldValueService;

    @Override
    public void importToDatabase(Cell cell, Patient patient, Map<Integer, Field> fields) throws ExcelImportException {
        Field field = fields.get(cell.getColumnIndex());

        if (field == null) {
            throw new ExcelImportException("Field not found with a given excel column index: " + cell.getColumnIndex());
        }

        FieldValue<?> fieldValue = fieldValueFactory.fromFieldType(field.getType());
        fieldValue.setPatient(patient);
        fieldValue.setField(field);
        fieldValue.setValueFromString(ExcelUtils.getValueAsString(cell));

        fieldValueService.save(fieldValue);

        log.info(String.format("[%d, %s] %s", patient.getId(), field.getName(), fieldValue.getValue()));
    }
}
