package com.medicalsystem.repository;

import com.medicalsystem.model.Field;
import com.medicalsystem.model.Form;
import com.medicalsystem.model.Section;
import com.medicalsystem.service.FormService;
import com.medicalsystem.util.GenUtils;
import org.junit.Before;
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

    @Autowired
    private FormService formService;

    @Before
    public void clearDatabase() {
        formRepository.delete(formRepository.findAll());
    }

    @Test
    public void test_autowire() {
        assertNotNull(formRepository);
    }

    @Test
    @Transactional
    public void test_cascadeSaving_and_findAll_using_repository() {
        final int formsToCreate = 2;
        final int sectionsToCreate = 5;
        final int fieldsToCreate = 7;

        List<Form> forms = GenUtils.createFormStructure(formsToCreate, sectionsToCreate, fieldsToCreate);

        formRepository.save(forms);
        forms = formRepository.findAll();
        assertNotNull(forms);
        assertEquals(formsToCreate, forms.size());

        testObjectStructure(sectionsToCreate, fieldsToCreate, forms);

        formRepository.delete(forms);
    }

    @Test
    @Transactional
    public void test_cascadeSaving_and_findAll_using_service() {
        final int formsToCreate = 2;
        final int sectionsToCreate = 5;
        final int fieldsToCreate = 7;

        List<Form> forms = GenUtils.createFormStructure(formsToCreate, sectionsToCreate, fieldsToCreate);

        forms.forEach(formService::save);

        forms = formService.getAll();
        assertNotNull(forms);
        assertEquals(formsToCreate, forms.size());

        testObjectStructure(sectionsToCreate, fieldsToCreate, forms);

        forms.forEach(form -> formService.deleteById(form.getId()));
    }

    private void testObjectStructure(int sectionsToCreate, int fieldsToCreate, List<Form> forms) {
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
