package com.medicalsystem.factory;

import com.medicalsystem.model.Patient;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.FieldValue;
import com.medicalsystem.model.value.MultipleFieldValue;
import com.medicalsystem.service.PatientService;
import com.medicalsystem.util.CellUtils;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log
public class FieldValueFactory {

    private static final String FIELD_VALUE_PACKAGE = "com.medicalsystem.model.value";
    private static final String FIELD_SUFFIX = "Field";
    private static final String FIELD_VALUE_SUFFIX = "FieldValue";

    private static PatientService patientService;

    @Autowired
    public FieldValueFactory(PatientService patientService) {
        FieldValueFactory.patientService = patientService;
    }

    /**
     * Creates a proper field value object
     *
     * @param field     a field which created value represents
     * @param cell      an excel cell to get value from
     * @param patientId patient id
     * @return          a proper field value object
     */
    public static FieldValue<?> createFieldValue(Field<?> field, Cell cell, int patientId) {
        FieldValue<?> fieldValue = createBlankObject(field);

        Patient patient = patientService.findById(patientId);

        fieldValue.setPatient(patient);
        fieldValue.setField(field);
        fieldValue.setStringValue(CellUtils.getStringValue(cell));

        return fieldValue;
    }

    /**
     * Creates a field value object with proper extension based on a given field object
     * E.g. for 'DateField' will create 'DateFieldValue', etc.
     *
     * @param field field object
     * @return      a proper field value object
     */
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
