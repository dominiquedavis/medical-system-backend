package com.medicalsystem.model.value;

import com.medicalsystem.model.field.DateField;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "DATE_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DateFieldValue extends FieldValue<DateField, LocalDate> {

    @Access(AccessType.PROPERTY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @Override
    public DateField getField() {
        return super.getField();
    }

    @Access(AccessType.PROPERTY)
    @Override
    public LocalDate getValue() {
        return super.getValue();
    }

}
