package com.medicalsystem.model.value;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.medicalsystem.json.serializer.LocalDateSerializer;
import com.medicalsystem.model.FormType;
import com.medicalsystem.util.DateUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "DATE_FIELDS_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DateFieldValue extends FieldValue<LocalDate> {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDate.class, new LocalDateSerializer());
        mapper.registerModule(module);
    }

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

    @Override
    public List<?> getValues() {
        String serialized = "";
        try {
            serialized = mapper.writeValueAsString(super.getValue()).replaceAll("\"", "");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Collections.singletonList(serialized);
    }
}
