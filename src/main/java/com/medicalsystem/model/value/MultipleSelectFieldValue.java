package com.medicalsystem.model.value;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Table(name = "MULTIPLE_SELECT_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MultipleSelectFieldValue extends FieldValue<List<String>> {

    @Access(AccessType.PROPERTY)
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "MULTIPLE_VALUES")
    @Column(name = "VALUE")
    @Override
    public List<String> getValue() {
        Map<String, String> possibleValues = super.getField().getPossibleValues();
        List<String> selectedKeys = super.getValue();
        // Return values associated with selectedKeys
        return selectedKeys.stream()
                .map(possibleValues::get)
                .collect(Collectors.toList());
    }
}
