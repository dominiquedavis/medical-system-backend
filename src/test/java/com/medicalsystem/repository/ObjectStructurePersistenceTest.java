package com.medicalsystem.repository;

import com.medicalsystem.model.Field;
import com.medicalsystem.model.Form;
import com.medicalsystem.model.Section;
import com.medicalsystem.util.GenUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectStructurePersistenceTest {

    @Autowired
    private FormRepository formRepository;

    @Test
    public void test_autowire() {
        assertNotNull(formRepository);
    }

    @Test
    @Transactional
    public void test_cascadeSaving_and_findAll() {
        final int formsToCreate = 2;
        final int sectionsToCreate = 5;
        final int fieldsToCreate = 7;

        List<Form> forms = GenUtils.createFormStructure(formsToCreate, sectionsToCreate, fieldsToCreate);

        formRepository.save(forms);
        forms = formRepository.findAll();
        assertNotNull(forms);
        assertEquals(formsToCreate, forms.size());

        for (Form form : forms) {
            List<Section> sections = form.getSections();
            assertNotNull(sections);
            assertEquals(sectionsToCreate, sections.size());

            for (Section section : sections) {
                List<Field> fields = section.getFields();
                assertNotNull(fields);
                assertEquals(fieldsToCreate, fields.size());
            }
        }
    }

}
