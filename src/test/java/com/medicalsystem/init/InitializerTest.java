package com.medicalsystem.init;

import com.medicalsystem.repository.FieldRepository;
import com.medicalsystem.repository.FormRepository;
import com.medicalsystem.repository.SectionRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitializerTest {

    @Autowired
    private InitializerImpl initializer;

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private FieldRepository fieldRepository;

    @Before @After
    public void clearDatabase() {
        formRepository.delete(formRepository.findAll());
    }

    @Test
    public void test_autowire() {
        assertNotNull(initializer);
    }

    @Test
    public void test_headersCreatedAndPersistedCorrectly() {
        long formCountBefore = formRepository.count();
        long sectionCountBefore = sectionRepository.count();
        long fieldCountBefore = fieldRepository.count();

        initializer.runInitialConfiguration();

        long formCountAfter = formRepository.count();
        long sectionCountAfter = sectionRepository.count();
        long fieldCountAfter = fieldRepository.count();

        assertTrue(formCountAfter > formCountBefore);
        assertTrue(sectionCountAfter > sectionCountBefore);
        assertTrue(fieldCountAfter > fieldCountBefore);
    }

}
