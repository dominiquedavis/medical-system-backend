package com.medicalsystem.model.fieldvalue;

import lombok.extern.java.Log;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "SELECT_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Log
public class SelectFieldValue extends FieldValue<String> {

    @Access(AccessType.PROPERTY)
    @Column(name = "VALUE")
    @Override
    public String getValue() {
        Map<String, String> possibleValues = getField().getPossibleValues();
        String selectedKey = super.getValue();
        return possibleValues.get(selectedKey);
    }

    @Override
    public void setValueFromString(String str) {
        str = str.trim();
        Map<String, String> possibleValues = getField().getPossibleValues();
        String value = possibleValues.get(str);
        if (value == null) {
            log.severe(String.format("[%s] Value not found for a given key: %s", getField().getName(), str));
        }
        super.setValue(value);
    }
}
