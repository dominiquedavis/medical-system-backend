package com.medicalsystem.model.value;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "DATE_FIELDS_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DateFieldValue extends FieldValue<Date> {

    @Access(AccessType.PROPERTY)
    @ElementCollection(fetch = FetchType.EAGER)
    @Override
    public List<Date> getValues() {
        return super.getValues();
    }
}
