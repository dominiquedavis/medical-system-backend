package com.medicalsystem.model.field;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "DATE_FIELDS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DateField extends Field<Date> {

    @ElementCollection
    @JoinTable(name = "DATE_FIELDS_OPTIONS")
    @MapKeyColumn(name = "excel_value")
    @Column(name = "date_value")
    @Access(AccessType.PROPERTY)
    @Override
    public Map<String, Date> getOptions() {
        return super.getOptions();
    }

}
