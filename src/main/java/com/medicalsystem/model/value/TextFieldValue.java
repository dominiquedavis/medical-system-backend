package com.medicalsystem.model.value;

import com.medicalsystem.model.FormType;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.field.TextField;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "TEXT_FIELDS_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class TextFieldValue extends FieldValue<String> {

    @Access(AccessType.PROPERTY)
    @Override
    public String getValue() {
        return super.getValue();
    }

    @Override
    public void setStringValue(String value) {
        super.setValue(value);
    }

    @Override
    public void createValueCell(Row row, FormType formType) {
        int columnIndex = super.getField().getExcelColumnByType(formType);
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(super.getValue());
    }

    @Override
    public List<?> getValues() {
        if (!getField().getOptions().isEmpty()) {
            TextField field = (TextField) getField();
            Map<String, String> possibleValues = field.getOptions();
            String key = super.getValue();
            return Collections.singletonList(possibleValues.get(key));
        }
        return Collections.singletonList(super.getValue());
    }

}
