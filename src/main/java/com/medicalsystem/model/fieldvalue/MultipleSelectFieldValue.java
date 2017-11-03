package com.medicalsystem.model.fieldvalue;

import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Cell;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Entity
@Table(name = "MULTIPLE_SELECT_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Log
public class MultipleSelectFieldValue extends FieldValue<List<String>> {

    @Access(AccessType.PROPERTY)
    @ElementCollection(targetClass = String.class,
            fetch = FetchType.EAGER)
    @CollectionTable(name = "MULTIPLE_VALUES")
    @Column(name = "VALUE")
    @Override
    public List<String> getValue() {
        return super.getValue();
    }

    @Override
    public void setValueFromString(String str) {
        List<String> values = new ArrayList<>();

        if (str.endsWith("11")) {
            values.add("11");
            str = str.substring(0, str.length() - 2);
        }

        for (char c : str.toCharArray()) {
            values.add(String.valueOf(c));
        }

        super.setValue(values);
    }

    @Override
    protected void createCellValue(Cell cell) {
        cell.setCellValue(getValue().stream().collect(Collectors.joining("")));
    }
}
