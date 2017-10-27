package com.medicalsystem.factory.impl;

import com.medicalsystem.factory.FieldFactory;
import com.medicalsystem.model.Field;
import com.medicalsystem.properties.FormProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FieldFactoryImpl implements FieldFactory {

    @Override
    public List<Field> fromProperties(List<FormProperties.Form.Section.Field> propertiesFields) {
        if (propertiesFields == null)
            return new ArrayList<>();

        return propertiesFields.stream()
                .map(this::fromProperties)
                .collect(Collectors.toList());
    }

    @Override
    public Field fromProperties(FormProperties.Form.Section.Field propertiesField) {
        if (propertiesField == null)
            return null;

        Field field = new Field();
        field.setName(propertiesField.getName());
        field.setColumnIndex(propertiesField.getColumnIndex());
        field.setType(propertiesField.getType());
        setPossibleValues(field, propertiesField);
        return field;
    }

    private void setPossibleValues(Field field, FormProperties.Form.Section.Field propertiesField) {
        Map<String, String> possibleValues = propertiesField.getOptions().stream()
                .collect(Collectors.toMap(
                        FormProperties.Form.Section.Field.Option::getKey,
                        FormProperties.Form.Section.Field.Option::getVal
                ));
        field.setPossibleValues(possibleValues);
    }
}
