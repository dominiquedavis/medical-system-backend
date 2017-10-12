package com.medicalsystem.model.value;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DOUBLE_FIELDS_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DoubleFieldValue extends FieldValue<Double> {

    @Access(AccessType.PROPERTY)
    @ElementCollection(fetch = FetchType.EAGER)
    @Override
    public List<Double> getValues() {
        return super.getValues();
    }

}
