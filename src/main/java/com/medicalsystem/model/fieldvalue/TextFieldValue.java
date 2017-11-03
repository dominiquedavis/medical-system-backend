package com.medicalsystem.model.fieldvalue;

import org.apache.poi.ss.usermodel.Cell;

import javax.persistence.*;

@Entity
@Table(name = "TEXT_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class TextFieldValue extends FieldValue<String> {

    @Access(AccessType.PROPERTY)
    @Column(name = "VALUE")
    @Override
    public String getValue() {
        return super.getValue();
    }

    @Override
    public void setValueFromString(String str) {
        super.setValue(str);
    }

    @Override
    protected void createCellValue(Cell cell) {
        cell.setCellValue(getValue());
    }
}
