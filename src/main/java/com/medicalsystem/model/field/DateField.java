package com.medicalsystem.model.field;

import lombok.NoArgsConstructor;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "DATE_FIELDS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public class DateField extends Field<Date> {

    public DateField(String name, int excelColumn, Map<Date, String> options) {
        super(name, excelColumn, options);
    }

    @ElementCollection
    @JoinTable(name = "DATE_FIELDS_OPTIONS")
    @MapKeyColumn(name = "excel_value")
    @Column(name = "date_value")
    @Access(AccessType.PROPERTY)
    @Override
    public Map<Date, String> getOptions() {
        return super.getOptions();
    }

    @Override
    public void addOption(String key, String value) {
        throw new NotImplementedException();
    }
}
