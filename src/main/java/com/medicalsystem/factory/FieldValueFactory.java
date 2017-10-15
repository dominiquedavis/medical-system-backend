package com.medicalsystem.factory;

import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.FieldValue;
import com.medicalsystem.model.value.MultipleFieldValue;
import com.medicalsystem.util.CellUtils;
import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Cell;

@Log
public class FieldValueFactory {

    private static final String FIELD_VALUE_PACKAGE = "com.medicalsystem.model.value";
    private static final String FIELD_SUFFIX = "Field";
    private static final String FIELD_VALUE_SUFFIX = "FieldValue";

    public static FieldValue<?> createFieldValue(Field<?> field, Cell cell, int patientId) {
        FieldValue<?> fieldValue = createBlankObject(field);

        fieldValue.setPatientId(patientId);
        fieldValue.setField(field);
        fieldValue.setStringValue(CellUtils.getStringValue(cell));

        return fieldValue;
    }

    private static FieldValue<?> createBlankObject(Field<?> field) {
        if (field.isMultiple())
            return new MultipleFieldValue();

        String fieldClassName = field.getClass().getSimpleName();
        String fieldValueClassName = FIELD_VALUE_PACKAGE + "." + fieldClassName.replaceAll(FIELD_SUFFIX, "") + FIELD_VALUE_SUFFIX;
        FieldValue<?> fieldValue = null;
        try {
            Class<?> c = Class.forName(fieldValueClassName);
            fieldValue = (FieldValue<?>) c.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            log.severe(e.getMessage());
        }
        return fieldValue;
    }

}