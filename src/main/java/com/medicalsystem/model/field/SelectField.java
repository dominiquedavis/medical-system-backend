package com.medicalsystem.model.field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "SELECT_FIELDS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public class SelectField extends Field {

    @ElementCollection
    @JoinTable(name = "SELECT_FIELDS_OPTIONS")
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    @Getter @Setter
    private Map<String, String> possibleValues = new HashMap<>();

    @Override
    public FieldType getType() {
        return FieldType.SELECT;
    }

}
