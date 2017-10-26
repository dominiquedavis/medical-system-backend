package com.medicalsystem.service;

import com.medicalsystem.model.fieldvalue.DateFieldValue;
import com.medicalsystem.model.fieldvalue.FieldValue;
import com.medicalsystem.model.fieldvalue.TextFieldValue;
import com.medicalsystem.service.impl.FieldValueServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FieldValueServiceImplTest {

    @Autowired
    private FieldValueServiceImpl fieldValueService;

    @Test
    public void test_autowire() {
        assertNotNull(fieldValueService);
    }

    @Test
    public void test_repositories_autowire() {
        fieldValueService.getAll();
    }

    @Test
    public void test_save_and_deleteById() {
        TextFieldValue fieldValue = new TextFieldValue();
        fieldValue.setField(null);
        fieldValue.setPatient(null);
        fieldValue.setValue("aaa");

        FieldValue<?> saved = fieldValueService.save(fieldValue);

        assertNotNull(saved);
        assertTrue(saved.getId() > 0);

        fieldValueService.deleteById(saved.getId());
        assertTrue(fieldValueService.getAll().isEmpty());
    }

    @Test
    public void test_getById() {
        DateFieldValue dateFieldValue = new DateFieldValue();
        dateFieldValue.setField(null);
        dateFieldValue.setPatient(null);
        dateFieldValue.setValue(LocalDate.now());

        long id = fieldValueService.save(dateFieldValue).getId();
        assertNotNull(fieldValueService.getById(id));

        fieldValueService.deleteById(id);
    }

    @Test
    public void test_existsById() {
        DateFieldValue dateFieldValue = new DateFieldValue();
        dateFieldValue.setField(null);
        dateFieldValue.setPatient(null);
        dateFieldValue.setValue(LocalDate.now());

        long id = fieldValueService.save(dateFieldValue).getId();
        assertTrue(fieldValueService.existsById(id));

        fieldValueService.deleteById(id);
    }

    @Test
    public void test_getAll() {
        TextFieldValue textFieldValue = new TextFieldValue();
        textFieldValue.setField(null);
        textFieldValue.setPatient(null);
        textFieldValue.setValue("aaa");

        DateFieldValue dateFieldValue = new DateFieldValue();
        dateFieldValue.setField(null);
        dateFieldValue.setPatient(null);
        dateFieldValue.setValue(LocalDate.now());

        fieldValueService.save(textFieldValue);
        fieldValueService.save(dateFieldValue);

        List<FieldValue<?>> fieldValues = fieldValueService.getAll();

        assertEquals(2, fieldValues.size());
    }

}
