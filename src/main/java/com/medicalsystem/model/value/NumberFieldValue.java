package com.medicalsystem.model.value;

import com.medicalsystem.model.field.NumberField;

import javax.persistence.*;

@Entity
@Table(name = "NUMBER_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class NumberFieldValue extends FieldValue<NumberField, Double> {

    @Access(AccessType.PROPERTY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @Override
    public NumberField getField() {
        return super.getField();
    }

    @Access(AccessType.PROPERTY)
    @Override
    public Double getValue() {
        return super.getValue();
    }

}
