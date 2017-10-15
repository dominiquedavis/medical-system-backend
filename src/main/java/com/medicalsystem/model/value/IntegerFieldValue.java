package com.medicalsystem.model.value;

import javax.persistence.*;

@Entity
@Table(name = "INTEGER_FIELDS_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class IntegerFieldValue extends FieldValue<Integer> {

    @Access(AccessType.PROPERTY)
    @Override
    public Integer getValues() {
        return super.getValues();
    }

}
