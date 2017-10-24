package com.medicalsystem.model.value;

import com.medicalsystem.model.field.SelectField;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "SELECT_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class SelectFieldValue extends FieldValue<SelectField, String> {

    @Access(AccessType.PROPERTY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @Override
    public SelectField getField() {
        return super.getField();
    }

    @Access(AccessType.PROPERTY)
    @Override
    public String getValue() {
        Map<String, String> possibleValues = getField().getPossibleValues();
        String selectedKey = super.getValue();
        return possibleValues.get(selectedKey);
    }

}
