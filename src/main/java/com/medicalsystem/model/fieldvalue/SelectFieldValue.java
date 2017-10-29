package com.medicalsystem.model.fieldvalue;

import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Cell;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "SELECT_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Log
public class SelectFieldValue extends FieldValue<String> {

    @Access(AccessType.PROPERTY)
    @Column(name = "VALUE")
    @Override
    public String getValue() {
        return super.getValue();
    }

    @Override
    public void setValueFromString(String str) {
        super.setValue(str.trim());
    }

    @Override
    protected void createCellValue(Cell cell) {
        cell.setCellValue(getValue());
    }
}
