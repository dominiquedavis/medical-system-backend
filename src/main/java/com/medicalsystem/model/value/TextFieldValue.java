package com.medicalsystem.model.value;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TEXT_FIELDS_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class TextFieldValue extends FieldValue<String> {

    @Access(AccessType.PROPERTY)
    @ElementCollection(fetch = FetchType.EAGER)
    @Override
    public List<String> getValues() {
        return super.getValues();
    }
}
