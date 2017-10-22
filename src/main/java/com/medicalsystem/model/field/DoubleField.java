package com.medicalsystem.model.field;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "DOUBLE_FIELDS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public class DoubleField extends Field<Double> {

    @ElementCollection
    @JoinTable(name = "DOUBLE_FIELDS_OPTIONS")
    @MapKeyColumn(name = "excel_value")
    @Column(name = "text_value")
    @Access(AccessType.PROPERTY)
    @Override
    public Map<Double, String> getOptions() {
        return super.getOptions();
    }

    @Override
    public void addOption(String key, String value) {
        double d = Double.parseDouble(key);
        super.addOption(d, value);
    }

    @Override
    protected String getTypeSub() {
        return "NUMBER";
    }
}
