package com.medicalsystem.model.value;

import com.medicalsystem.util.DateUtils;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "DATE_FIELDS_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DateFieldValue extends FieldValue<LocalDate> {

    @Access(AccessType.PROPERTY)
    @Override
    public LocalDate getValue() {
        return super.getValue();
    }

    @Override
    public void setStringValue(String value) {
        super.setValue(DateUtils.fromString(value));
    }
}
