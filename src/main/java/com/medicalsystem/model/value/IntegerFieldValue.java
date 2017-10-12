package com.medicalsystem.model.value;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "INTEGER_FIELDS_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class IntegerFieldValue extends FieldValue<Integer> {

    @Access(AccessType.PROPERTY)
    @ElementCollection(fetch = FetchType.EAGER)
    @Override
    public List<Integer> getValues() {
        return super.getValues();
    }

}
