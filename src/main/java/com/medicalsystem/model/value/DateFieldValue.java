package com.medicalsystem.model.value;

import com.medicalsystem.util.DateUtils;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DATE_FIELDS_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DateFieldValue extends FieldValue<Date> {

    @Access(AccessType.PROPERTY)
    @Override
    public Date getValue() {
        return super.getValue();
    }

    @Override
    public void setStringValue(String value) {
        super.setValue(DateUtils.fromString(value));
    }
}
