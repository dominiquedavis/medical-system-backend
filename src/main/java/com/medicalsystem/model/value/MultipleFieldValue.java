package com.medicalsystem.model.value;

import com.medicalsystem.model.FormType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "MULTIPLE_FIELDS_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MultipleFieldValue extends FieldValue<List<Integer>> {

    @Access(AccessType.PROPERTY)
    @ElementCollection(fetch = FetchType.EAGER)
    @Override
    public List<Integer> getValue() {
        return super.getValue();
    }

    @Override
    public void setStringValue(String value) {
        List<Integer> values = new ArrayList<>();

        if (value.endsWith("11")) {
            values.add(11);
            value = value.substring(0, value.length() - 2);
        }

        for (char c : value.toCharArray()) {
            int val = Character.getNumericValue(c);
            values.add(val);
        }

        super.setValue(values);
    }

    @Override
    public void createValueCell(Row row, FormType formType) {
        int columnIndex = super.getField().getExcelColumnByType(formType);
        Cell cell = row.createCell(columnIndex);
        List<Integer> values = super.getValue();
        String valuesAsString = values.stream().map(i -> Integer.toString(i)).collect(Collectors.joining());
        cell.setCellValue(Integer.parseInt(valuesAsString));
    }

}
