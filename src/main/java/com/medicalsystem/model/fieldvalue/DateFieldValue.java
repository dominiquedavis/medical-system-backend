package com.medicalsystem.model.fieldvalue;

import com.medicalsystem.util.DateUtils;
import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "DATE_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Log
public class DateFieldValue extends FieldValue<LocalDate> {

    @Access(AccessType.PROPERTY)
    @Column(name = "VALUE")
    @Override
    public LocalDate getValue() {
        return super.getValue();
    }

    @Override
    public void setValueFromString(String str) {
        LocalDate value = DateUtils.fromString(str);
        super.setValue(value);
    }

    @Override
    public void createCellValue(Cell cell) {
        Date date = DateUtils.toUtilDate(getValue());
        if (date == null) {
            log.severe("Date is null: " + getField().getName());
            cell.setCellValue("");
        } else {
            cell.setCellValue(date);
        }
    }
}
