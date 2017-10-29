package com.medicalsystem.service;

import com.medicalsystem.excel.importer.ExcelImporter;
import com.medicalsystem.init.Initializer;
import com.medicalsystem.model.Field;
import com.medicalsystem.model.Patient;
import com.medicalsystem.model.fieldvalue.DateFieldValue;
import com.medicalsystem.model.fieldvalue.FieldValue;
import com.medicalsystem.model.fieldvalue.SelectFieldValue;
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

    @Autowired
    private PatientService patientService;

    @Autowired
    private Initializer initializer;

    @Autowired
    private FormService formService;

    @Autowired
    private ExcelImporter excelImporter;

    @Autowired
    private FieldService fieldService;

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

    @Test
    public void test_getAllByPatient() {
        fieldValueService.getAll().forEach(fv -> fieldValueService.deleteById(fv.getId()));

        Patient patient = patientService.save(new Patient());

        TextFieldValue textFieldValue = new TextFieldValue();
        textFieldValue.setPatient(patient);

        DateFieldValue dateFieldValue = new DateFieldValue();
        dateFieldValue.setPatient(patient);

        SelectFieldValue selectFieldValue = new SelectFieldValue();
        selectFieldValue.setPatient(null);

        fieldValueService.save(textFieldValue);
        fieldValueService.save(dateFieldValue);

        assertEquals(2, fieldValueService.getAllByPatient(patient).size());

        fieldValueService.getAll().forEach(fv -> fieldValueService.deleteById(fv.getId()));
    }

    @Test
    public void test_getByPatientAndField() {
        initializer.runInitialConfiguration();
        excelImporter.importToDatabase("data/baza2.xslx");

        Patient patient = patientService.getById(1L);
        Field field = fieldService.getById(3L);

        FieldValue<?> fieldValue = fieldValueService.getByPatientAndField(patient, field);
        //assertNotNull(fieldValue);

        fieldValueService.getAll().forEach(fv -> fieldValueService.deleteById(fv.getId()));
        formService.getAll().forEach(form -> formService.deleteById(form.getId()));
    }
}
