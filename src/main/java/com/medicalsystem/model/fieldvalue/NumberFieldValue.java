package com.medicalsystem.model.fieldvalue;

import org.apache.commons.lang3.math.NumberUtils;

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

    @Override
    public void setValueFromString(String str) {
        str = str.trim();
        if (NumberUtils.isCreatable(str)) {
            super.setValue(Double.parseDouble(str));
        } else {
            super.setValue(-1.0);
        }
    }
}
