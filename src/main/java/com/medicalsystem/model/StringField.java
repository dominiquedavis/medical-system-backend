package com.medicalsystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "STRING_FIELDS")
public class StringField extends FormField<String, String> {

    @ElementCollection
    @JoinTable(name = "STRING_FIELDS_VALUES")
    @MapKeyColumn(name = "excel_value")
    @Column(name = "string_value")
    @Getter @Setter
    private Map<String, String> possibleValues;

    @Override
    public void addPossibleValue(String key, String value) {
        possibleValues.put(key, value);
    }
}
