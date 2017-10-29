package com.medicalsystem.mapper.impl;

import com.medicalsystem.mapper.FieldMapper;
import com.medicalsystem.model.Field;
import com.medicalsystem.model.FieldType;
import com.medicalsystem.model.json.JSONField;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FieldMapperImpl implements FieldMapper {

    @Override
    public List<JSONField> toJSONs(List<Field> fields) {
        return fields.stream().map(this::toJSON).collect(Collectors.toList());
    }

    @Override
    public JSONField toJSON(Field field) {
        return JSONField.builder()
                .id(field.getId())
                .name(field.getName())
                .type(field.getType().name())
                .values(null)
                .possibleValues(null)
                .build();
    }

    @Override
    public Field fromJSON(JSONField jsonField) {
        Field field = new Field();
        field.setName(jsonField.getName());
        field.setType(FieldType.valueOf(jsonField.getType()));
        // TODO
        //field.setPossibleValues();
        return field;
    }
}
