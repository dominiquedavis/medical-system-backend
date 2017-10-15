package com.medicalsystem.model.value;

import lombok.extern.java.Log;
import org.apache.commons.lang3.math.NumberUtils;

import javax.persistence.*;

@Entity
@Table(name = "INTEGER_FIELDS_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Log
public class IntegerFieldValue extends FieldValue<Integer> {

    @Access(AccessType.PROPERTY)
    @Override
    public Integer getValue() {
        return super.getValue();
    }

    @Override
    public void setStringValue(String value) {
        int val;
        try {
            val = NumberUtils.isCreatable(value) ? NumberUtils.createInteger(value) : -1;
        } catch (NumberFormatException e) {
            val = -1;
            log.severe(e.getMessage());
        }
        super.setValue(val);
    }
}
