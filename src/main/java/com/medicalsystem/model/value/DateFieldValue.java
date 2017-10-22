package com.medicalsystem.model.value;

import com.medicalsystem.model.FormType;
import com.medicalsystem.util.DateUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "DATE_FIELDS_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DateFieldValue extends FieldValue<LocalDate> {

    @Access(AccessType.PROPERTY)
    @Override
    public LocalDate getValue() {
        return super.getValue();
    }

    @Override
    public void setStringValue(String value) {
        super.setValue(DateUtils.fromString(value));
    }

    @Override
    public void createValueCell(Row row, FormType formType) {
        int columnIndex = super.getField().getExcelColumnByType(formType);
        Cell cell = row.createCell(columnIndex);
        LocalDate localDate = super.getValue();
        Date utilDate = DateUtils.fromLocalDate(localDate);
        cell.setCellValue(utilDate);
    }
}
