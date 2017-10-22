package com.medicalsystem.model.field;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "TEXT_FIELDS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public class TextField extends Field<String> {

    @ElementCollection
    @JoinTable(name = "TEXT_FIELDS_OPTIONS")
    @MapKeyColumn(name = "excel_value")
    @Column(name = "text_value")
    @Access(AccessType.PROPERTY)
    @Override
    public Map<String, String> getOptions() {
        return super.getOptions();
    }

    @Override
    public void addOption(String key, String value) {
        super.addOption(key, value);
    }

    @Override
    protected String getTypeSub() {
        return "TEXT";
    }
}
