package com.medicalsystem.json.mapper.impl;

import com.medicalsystem.json.mapper.FieldMapper;
import com.medicalsystem.json.model.JSONField;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.FieldValue;
import com.medicalsystem.service.FieldValueService;
import com.medicalsystem.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class FieldMapperImpl implements FieldMapper {

    private final FieldValueService fieldValueService;
    private final PatientService patientService;

    @Override
    public Field<?> fromJSON(JSONField jsonField) {
        /*Field<?> field = fieldService.findById(jsonField.getId());

        if (field == null) {
            log.severe("Field not found with ID: " + jsonField.getId());
            return null;
        }

        Patient patient = patientService.findById(patientId);

        if (patient == null) {
            log.severe("Patient not found with ID: " + patientId);
            return null;
        }

        // TODO
        switch (jsonField.getType()) {
            case "DATE":
                DateFieldValue fieldValue = new DateFieldValue();
                fieldValue.setId(jsonField.getId());
                //fieldValue.setPatient(patient);
                fieldValue.setField(field);
                String value = (String) jsonField.getValues().get(0);
                fieldValue.setValue(DateUtils.fromString(value));
                break;
        }
        */

        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public JSONField toJSON(Field<?> field) {
        JSONField jsonField = new JSONField();

        jsonField.setId(field.getId());
        jsonField.setName(field.getName());
        jsonField.setType(field.getType());
        jsonField.setValues(null);
        jsonField.setPossibleValues(null);

        return jsonField;
    }

    @Override
    public JSONField toJSON(Field<?> field, int patientId) {
        FieldValue<?> fieldValue = fieldValueService.findByFieldAndPatientId(field, patientId);
        return fieldValue.createJSONField();
    }
}
