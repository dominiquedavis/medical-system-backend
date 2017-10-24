package com.medicalsystem.model.value;

import com.medicalsystem.model.field.MultipleSelectField;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Table(name = "MULTIPLE_SELECT_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MultipleSelectFieldValue extends FieldValue<MultipleSelectField, List<String>> {

    @Access(AccessType.PROPERTY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @Override
    public MultipleSelectField getField() {
        return super.getField();
    }

    @Override
    public List<String> getValue() {
        Map<String, String> possibleValues = getField().getPossibleValues();
        List<String> selectedKeys = super.getValue();
        // Return values associated with selectedKeys
        return selectedKeys.stream()
                .map(possibleValues::get)
                .collect(Collectors.toList());
    }

}
