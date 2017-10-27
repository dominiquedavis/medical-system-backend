package com.medicalsystem.properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FormPropertiesTest {

    @Autowired
    private FormProperties formProperties;

    @Test
    public void test_autowire() {
        assertNotNull(formProperties);
    }

    @Test
    public void test_binding() {
        testForms(formProperties.getForms());
    }

    private void testForms(List<FormProperties.Form> forms) {
        assertNotNull(forms);
        assertFalse(forms.isEmpty());

        forms.forEach(form -> {
            assertNotNull(form.getName());
            testSections(form.getSections());
        });
    }

    private void testSections(List<FormProperties.Form.Section> sections) {
        assertNotNull(sections);
        assertFalse(sections.isEmpty());

        sections.forEach(section -> {
            assertNotNull(section.getName());
            testFields(section.getFields());
        });
    }

    private void testFields(List<FormProperties.Form.Section.Field> fields) {
        assertNotNull(fields);
        assertFalse(fields.isEmpty());

        fields.forEach(field -> {
            assertNotNull(field.getName());
            assertNotNull(field.getType());
            testOptions(field.getOptions());
        });
    }

    private void testOptions(List<FormProperties.Form.Section.Field.Option> options) {
        assertNotNull(options);
    }

}
