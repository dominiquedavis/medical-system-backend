package com.medicalsystem.model.value;

import org.apache.commons.lang3.math.NumberUtils;

import javax.persistence.*;

@Entity
@Table(name = "DOUBLE_FIELDS_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DoubleFieldValue extends FieldValue<Double> {

    @Access(AccessType.PROPERTY)
    @Override
    public Double getValue() {
        return super.getValue();
    }

    @Override
    public void setStringValue(String value) {
        double val = NumberUtils.isCreatable(value) ? NumberUtils.createDouble(value) : -1;
        super.setValue(val);
    }
}
