package com.medicalsystem.model.value;

import javax.persistence.*;

@Entity
@Table(name = "TEXT_FIELDS_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class TextFieldValue extends FieldValue<String> {

    @Access(AccessType.PROPERTY)
    @Override
    public String getValue() {
        return super.getValue();
    }

    @Override
    public void setStringValue(String value) {
        super.setValue(value);
    }
}
