package com.medicalsystem.model.field;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "DOUBLE_FIELDS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public class DoubleField extends Field<Double> {

    public DoubleField(String name, int excelColumn, Map<String, Double> options) {
        super(name, excelColumn, options);
    }

    @ElementCollection
    @JoinTable(name = "DOUBLE_FIELDS_OPTIONS")
    @MapKeyColumn(name = "excel_value")
    @Column(name = "text_value")
    @Access(AccessType.PROPERTY)
    @Override
    public Map<String, Double> getOptions() {
        return super.getOptions();
    }

}
