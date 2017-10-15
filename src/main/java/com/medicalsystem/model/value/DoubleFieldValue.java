package com.medicalsystem.model.value;

import javax.persistence.*;

@Entity
@Table(name = "DOUBLE_FIELDS_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DoubleFieldValue extends FieldValue<Double> {

    @Access(AccessType.PROPERTY)
    @Override
    public Double getValues() {
        return super.getValues();
    }

}
