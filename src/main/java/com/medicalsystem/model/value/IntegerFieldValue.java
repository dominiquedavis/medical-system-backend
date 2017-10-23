package com.medicalsystem.model.value;

import com.medicalsystem.model.FormType;
import lombok.extern.java.Log;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "INTEGER_FIELDS_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Log
public class IntegerFieldValue extends FieldValue<Integer> {

    @Access(AccessType.PROPERTY)
    @Override
    public Integer getValue() {
        return super.getValue();
    }

    @Override
    public void setStringValue(String value) {
        int val;
        try {
            val = NumberUtils.isCreatable(value) ? NumberUtils.createInteger(value) : -1;
        } catch (NumberFormatException e) {
            val = -1;
            log.severe(e.getMessage());
        }
        super.setValue(val);
    }

    @Override
    public void createValueCell(Row row, FormType formType) {
        int columnIndex = super.getField().getExcelColumnByType(formType);
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(super.getValue());
    }

    @Override
    public List<?> getValues() {
        return Collections.singletonList(super.getValue());
    }
}
