package com.medicalsystem.json.mapper.impl;

import com.medicalsystem.json.mapper.FieldMapper;
import com.medicalsystem.json.model.JSONField;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.FieldValue;
import com.medicalsystem.service.FieldValueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FieldMapperImpl implements FieldMapper {

    private final FieldValueService fieldValueService;

    @Override
    public Field<?> fromJSON(JSONField jsonField) {
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
