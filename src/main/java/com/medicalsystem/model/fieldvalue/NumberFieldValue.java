package com.medicalsystem.model.fieldvalue;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.persistence.*;

@Entity
@Table(name = "NUMBER_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class NumberFieldValue extends FieldValue<Double> {

    @Access(AccessType.PROPERTY)
    @Column(name = "VALUE")
    @Override
    public Double getValue() {
        return super.getValue();
    }

    @Override
    public void setValueFromString(String str) {
        Double value = NumberUtils.isCreatable(str) ? Double.parseDouble(str) : -1;
        super.setValue(value);
    }

    @Override
    protected void createCellValue(Cell cell) {
        cell.setCellValue(getValue());
    }
}
