package com.medicalsystem.model.field;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "INTEGER_FIELDS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public class IntegerField extends Field<Integer> {

    public IntegerField(String name, int excelColumn, Map<String, Integer> options) {
        super(name, excelColumn, options);
    }

    @ElementCollection
    @JoinTable(name = "INTEGER_FIELDS_OPTIONS")
    @MapKeyColumn(name = "excel_value")
    @Column(name = "text_value")
    @Access(AccessType.PROPERTY)
    @Override
    public Map<String, Integer> getOptions() {
        return super.getOptions();
    }

}
