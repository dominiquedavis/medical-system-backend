package com.medicalsystem.mapper.impl;

import com.medicalsystem.mapper.FieldMapper;
import com.medicalsystem.model.Field;
import com.medicalsystem.model.FieldType;
import com.medicalsystem.model.Patient;
import com.medicalsystem.model.fieldvalue.DateFieldValue;
import com.medicalsystem.model.fieldvalue.FieldValue;
import com.medicalsystem.model.fieldvalue.MultipleSelectFieldValue;
import com.medicalsystem.model.fieldvalue.SelectFieldValue;
import com.medicalsystem.model.json.JSONField;
import com.medicalsystem.service.FieldValueService;
import com.medicalsystem.util.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FieldMapperImpl implements FieldMapper {

    private final FieldValueService fieldValueService;

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

    @Override
    public List<JSONField> toJSONs(List<Field> fields, Patient patient) {
        return fields.stream().map(field -> toJSON(field, patient)).collect(Collectors.toList());
    }

    @Override
    public JSONField toJSON(Field field, Patient patient) {
        System.out.println("Field: " + field.getName());
        FieldValue<?> fieldValue = fieldValueService.getByPatientAndField(patient, field);
        return JSONField.builder()
                .id(field.getId())
                .name(field.getName())
                .type(field.getType().name())
                .values(getValues(fieldValue))
                .possibleValues(getPossibleValues(field))
                .build();
    }

    private List<?> getValues(FieldValue<?> fieldValue) {
        Field field = fieldValue.getField();
        if (field.getType() == FieldType.SELECT) {
            Map<String, String> possibleValues = field.getPossibleValues();
            String key = ((SelectFieldValue) fieldValue).getValue();
            return Collections.singletonList(possibleValues.get(key));
        }
        else if (field.getType() == FieldType.MULTIPLE_SELECT) {
            Map<String, String> possibleValues = field.getPossibleValues();
            List<String> keys = ((MultipleSelectFieldValue) fieldValue).getValue();
            return keys.stream().map(possibleValues::get).collect(Collectors.toList());
        }
        else if (field.getType() == FieldType.DATE) {
            LocalDate localDate = ((DateFieldValue) fieldValue).getValue();
            return Collections.singletonList(DateUtils.toUtilDate(localDate));
        }
        return Collections.singletonList(fieldValue.getValue());
    }

    private List<?> getPossibleValues(Field field) {
        return new ArrayList<>(field.getPossibleValues().values());
    }
}
