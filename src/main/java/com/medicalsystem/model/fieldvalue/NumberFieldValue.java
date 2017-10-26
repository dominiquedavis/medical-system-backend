package com.medicalsystem.model.fieldvalue;

import javax.persistence.*;

@Entity
@Table(name = "NUMBER_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class NumberFieldValue extends FieldValue<Double> {

    @Access(AccessType.PROPERTY)
    @Column(name = "VALUE")
    @Override
    public Double getValue() {
        return super.getValue();
    }
}
