package com.medicalsystem.model.value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MULTIPLE_FIELDS_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MultipleFieldValue extends FieldValue<List<Integer>> {

    @Access(AccessType.PROPERTY)
    @ElementCollection(fetch = FetchType.EAGER)
    @Override
    public List<Integer> getValue() {
        return super.getValue();
    }

    @Override
    public void setStringValue(String value) {
        List<Integer> values = new ArrayList<>();

        if (value.endsWith("11")) {
            values.add(11);
            value = value.substring(0, value.length() - 2);
        }

        for (char c : value.toCharArray()) {
            int val = Character.getNumericValue(c);
            values.add(val);
        }

        super.setValue(values);
    }
}
