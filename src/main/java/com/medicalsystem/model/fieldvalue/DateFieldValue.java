package com.medicalsystem.model.fieldvalue;

import com.medicalsystem.util.DateUtils;
import com.medicalsystem.util.ExcelUtils;
import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Cell;

import javax.persistence.*;
import java.text.ParseException;
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
        LocalDate value = null;
        try {
            value = DateUtils.fromString(str);
        } catch (ParseException e) {
            log.severe(e.getMessage());
        }
        super.setValue(value);
    }

    @Override
    public void createCellValue(Cell cell) {
        ExcelUtils.setDateCellStyle(cell);

        Date date = DateUtils.toUtilDate(getValue());
        System.out.println("Setting date: " + date);
        if (date == null) {
            log.severe("Date is null: " + getField().getName());
            cell.setCellValue("");
        } else {
            cell.setCellValue(date);
        }
    }
}
