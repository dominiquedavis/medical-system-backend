package com.medicalsystem.json.mapper.impl;

import com.medicalsystem.json.mapper.FieldMapper;
import com.medicalsystem.json.model.JSONField;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.FieldValue;
import com.medicalsystem.service.FieldService;
import com.medicalsystem.service.FieldValueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FieldMapperImpl implements FieldMapper {

    private final FieldService fieldService;
    private final FieldValueService fieldValueService;

    @Override
    public Field<?> fromJSON(JSONField jsonField) {
        Field<?> field = fieldService.findById(jsonField.getId());
        // TODO: Implement
        return field;
    }

    @Override
    public JSONField toJSON(Field<?> field) {
        return null;
    }

    @Override
    public JSONField toJSON(Field<?> field, int patientId) {
        FieldValue<?> fieldValue = fieldValueService.findByFieldAndPatientId(field, patientId);
        return fieldValue.createJSONField();
    }
}
