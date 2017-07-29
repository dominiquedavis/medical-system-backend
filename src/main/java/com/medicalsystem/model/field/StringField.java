package com.medicalsystem.model.field;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "STRING_FIELDS")
public class StringField extends Field<String> {

    @ElementCollection
    @JoinTable(name = "STRING_FIELDS_OPTIONS")
    @MapKeyColumn(name = "excel_value")
    @Column(name = "string_value")
    @Access(AccessType.PROPERTY)
    @Override
    public Map<String, String> getOptions() {
        return super.getOptions();
    }

}
