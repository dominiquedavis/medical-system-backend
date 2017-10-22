package com.medicalsystem.json.mapper;

import com.medicalsystem.json.model.JSONField;
import com.medicalsystem.model.field.Field;

public interface FieldMapper {

    Field<?> fromJSON(JSONField jsonField);

    JSONField toJSON(Field<?> field);

}
