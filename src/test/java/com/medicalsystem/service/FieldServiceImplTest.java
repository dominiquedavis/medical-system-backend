package com.medicalsystem.service;

import com.medicalsystem.init.Initializer;
import com.medicalsystem.model.Form;
import com.medicalsystem.service.impl.FieldServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FieldServiceImplTest {

    @Autowired
    private FieldServiceImpl fieldService;

    @Autowired
    private Initializer initializer;

    @Autowired
    private FormService formService;

    @Before
    public void setup() {
        initializer.runInitialConfiguration();
    }

    @After
    public void tearDown() {
        fieldService.getAll().forEach(field -> fieldService.deleteById(field.getId()));
    }

    @Test
    public void test_getNextExcelColumnIndex() {
        Form form = formService.getAll().get(0);
        int nextExcelColumnIndex = fieldService.getNextExcelColumnIndex(form);
        long fieldsInForm = form.getSections().stream().mapToLong(s -> s.getFields().size()).sum();

        assertEquals(fieldsInForm + 1, nextExcelColumnIndex);
    }
}
