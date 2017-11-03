package com.medicalsystem.model.fieldvalue;

import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Cell;

import javax.persistence.*;

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
        if (getField().getPossibleValues().containsKey(str)) {
            super.setValue(str);
        } else {
            super.setValue(null);
        }
    }

    @Override
    protected void createCellValue(Cell cell) {
        cell.setCellValue(getValue());
    }
}
