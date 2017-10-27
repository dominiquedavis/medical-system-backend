package com.medicalsystem.model.fieldvalue;

import lombok.extern.java.Log;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Table(name = "MULTIPLE_SELECT_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Log
public class MultipleSelectFieldValue extends FieldValue<List<String>> {

    @Access(AccessType.PROPERTY)
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "MULTIPLE_VALUES")
    @Column(name = "VALUE")
    @Override
    public List<String> getValue() {
        Map<String, String> possibleValues = super.getField().getPossibleValues();
        List<String> selectedKeys = super.getValue();
        // Return values associated with selectedKeys
        return selectedKeys.stream()
                .map(possibleValues::get)
                .collect(Collectors.toList());
    }

    @Override
    public void setValueFromString(String str) {
        str = str.trim();
        Map<String, String> possibleValues = getField().getPossibleValues();

        List<String> values = new ArrayList<>();

        if (str.endsWith("11")) {
            values.add(possibleValues.get("11"));
            if (values.get(0) == null) {
                logValueNotFound(getField().getName(), str);
            }
            str = str.substring(0, str.length() - 2);
        }

        for (char c : str.toCharArray()) {
            String key = String.valueOf(c);
            String value = possibleValues.get(key);
            if (value == null) {
                logValueNotFound(getField().getName(), key);
            }
            values.add(value);
        }

        super.setValue(values);
    }

    private void logValueNotFound(String fieldName, String key) {
        log.severe(String.format("[%s] Value not found for a given key: %s", fieldName, key));
    }
}
