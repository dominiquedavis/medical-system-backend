package com.medicalsystem.model.fieldvalue;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "SELECT_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class SelectFieldValue extends FieldValue<String> {

    @Access(AccessType.PROPERTY)
    @Column(name = "VALUE")
    @Override
    public String getValue() {
        Map<String, String> possibleValues = super.getField().getPossibleValues();
        String selectedKey = super.getValue();
        return possibleValues.get(selectedKey);
    }
}
