package com.medicalsystem.factory;

import com.medicalsystem.factory.impl.FieldFactoryImpl;
import com.medicalsystem.factory.impl.FormFactoryImpl;
import com.medicalsystem.factory.impl.SectionFactoryImpl;
import com.medicalsystem.model.Field;
import com.medicalsystem.model.FieldType;
import com.medicalsystem.model.Form;
import com.medicalsystem.model.Section;
import com.medicalsystem.properties.FormProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FactoryFromPropertiesTest {

    @Autowired
    private FormFactoryImpl formFactory;

    @Autowired
    private SectionFactoryImpl sectionFactory;

    @Autowired
    private FieldFactoryImpl fieldFactory;

    @Autowired
    private FormProperties formProperties;

    @Test
    public void test_autowire() {
        assertNotNull(formFactory);
    }

    @Test
    public void test_formFactory_nullArgument() {
        List<Form> forms = formFactory.fromProperties((List<FormProperties.Form>) null);
        Form form = formFactory.fromProperties((FormProperties.Form) null);

        assertNotNull(forms);
        assertTrue(forms.isEmpty());
        assertNull(form);
    }

    @Test
    public void test_sectionFactory_nullArgument() {
        List<Section> sections = sectionFactory.fromProperties((List<FormProperties.Form.Section>) null);
        Section section = sectionFactory.fromProperties((FormProperties.Form.Section) null);

        assertNotNull(sections);
        assertTrue(sections.isEmpty());
        assertNull(section);
    }

    @Test
    public void test_fieldFactory_nullArgument() {
        List<Field> fields = fieldFactory.fromProperties((List<FormProperties.Form.Section.Field>) null);
        Field field = fieldFactory.fromProperties((FormProperties.Form.Section.Field) null);

        assertNotNull(fields);
        assertTrue(fields.isEmpty());
        assertNull(field);
    }

    @Test
    public void test_Form_fromProperties() {
        List<Form> forms = formFactory.fromProperties(formProperties.getForms());

        assertNotNull(forms);
        assertFalse(forms.isEmpty());

        forms.forEach(form -> {
            assertNotNull(form);
            test_Section_fromProperties(form.getSections());
        });
    }

    private void test_Section_fromProperties(List<Section> sections) {
        assertNotNull(sections);
        assertFalse(sections.isEmpty());

        sections.forEach(section -> {
            assertNotNull(section);
            test_Field_fromProperties(section.getFields());
        });
    }

    private void test_Field_fromProperties(List<Field> fields) {
        assertFalse(fields.isEmpty());

        fields.forEach(field -> {
            assertNotNull(field);
            if (field.getType() == FieldType.SELECT || field.getType() == FieldType.MULTIPLE_SELECT) {
                assertFalse(field.getPossibleValues().isEmpty());
            } else {
                assertTrue(field.getPossibleValues().isEmpty());
            }
        });
    }
}
