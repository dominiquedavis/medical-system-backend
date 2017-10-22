package com.medicalsystem.model.value;

import com.medicalsystem.model.FormType;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.persistence.*;

@Entity
@Table(name = "DOUBLE_FIELDS_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DoubleFieldValue extends FieldValue<Double> {

    @Access(AccessType.PROPERTY)
    @Override
    public Double getValue() {
        return super.getValue();
    }

    @Override
    public void setStringValue(String value) {
        double val = NumberUtils.isCreatable(value) ? NumberUtils.createDouble(value) : -1;
        super.setValue(val);
    }

    @Override
    public void createValueCell(Row row, FormType formType) {
        int columnIndex = super.getField().getExcelColumnByType(formType);
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(super.getValue());
    }

}
