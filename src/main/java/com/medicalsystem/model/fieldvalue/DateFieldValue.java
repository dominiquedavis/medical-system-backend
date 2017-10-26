package com.medicalsystem.model.fieldvalue;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "DATE_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DateFieldValue extends FieldValue<LocalDate> {

    @Access(AccessType.PROPERTY)
    @Column(name = "VALUE")
    @Override
    public LocalDate getValue() {
        return super.getValue();
    }
}
