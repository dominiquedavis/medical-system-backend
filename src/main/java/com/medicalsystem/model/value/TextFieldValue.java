package com.medicalsystem.model.value;

import com.medicalsystem.model.field.TextField;

import javax.persistence.*;

@Entity
@Table(name = "TEXT_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class TextFieldValue extends FieldValue<TextField, String> {

    @Access(AccessType.PROPERTY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @Override
    public TextField getField() {
        return super.getField();
    }

    @Access(AccessType.PROPERTY)
    @Override
    public String getValue() {
        return super.getValue();
    }

}
