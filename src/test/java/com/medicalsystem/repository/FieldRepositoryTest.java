package com.medicalsystem.repository;

import com.medicalsystem.init.Initializer;
import com.medicalsystem.model.Field;
import com.medicalsystem.model.FieldType;
import com.medicalsystem.model.Form;
import com.medicalsystem.util.GenUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FieldRepositoryTest {

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private Initializer initializer;

    @Autowired
    private FormRepository formRepository;

    @Test
    public void test_autowire() {
        assertNotNull(fieldRepository);
    }

    @Test
    public void test_save_and_findOne() {
        Field field = GenUtils.getRandomField();

        field = fieldRepository.save(field);
        field = fieldRepository.findOne(field.getId());

        assertNotNull(field);
    }

    @Test
    public void test_findAll() {
        final long fieldCountBefore = fieldRepository.count();
        final int fieldsToSave = 10;

        for (int i = 0; i < fieldsToSave; i++) {
            Field field = GenUtils.getRandomField();
            fieldRepository.save(field);
        }

        List<Field> fields = fieldRepository.findAll();

        assertNotNull(fields);
        assertEquals(fieldsToSave + fieldCountBefore, fields.size());
    }

    @Test
    public void test_findAllByForm() {
        formRepository.delete(formRepository.findAll());

        initializer.runInitialConfiguration();
        Form form = formRepository.findAll().get(0);
        List<Field> fields = fieldRepository.findAllByForm(form);

        assertNotNull(fields);
        assertFalse(fields.isEmpty());

        System.out.println(fields.size());

        formRepository.delete(formRepository.findAll());
    }

}
