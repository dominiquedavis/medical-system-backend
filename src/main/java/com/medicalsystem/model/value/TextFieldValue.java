package com.medicalsystem.model.value;

import javax.persistence.*;

@Entity
@Table(name = "TEXT_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class TextFieldValue extends FieldValue<String> {

    @Access(AccessType.PROPERTY)
    @Column(name = "VALUE")
    @Override
    public String getValue() {
        return super.getValue();
    }
}
