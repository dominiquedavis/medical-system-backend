package com.medicalsystem.properties;

import com.medicalsystem.model.field.Field;
import com.medicalsystem.service.FieldService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigPropertiesTest {

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private FieldService fieldService;

    @Test
    public void propertiesShouldLoadCorrectly() {
        List<ConfigProperties.Section> sections = configProperties.getSections();
        List<ConfigProperties.Section.Field> fields = sections.stream()
                .flatMap(s -> s.getFields().stream())
                .collect(Collectors.toList());
        List<String> fieldNames = fields.stream()
                .map(ConfigProperties.Section.Field::getName)
                .collect(Collectors.toList());

        Assert.assertTrue(fieldNames.contains("Endoleak"));

        Field<?> endoleak = fieldService.findByName("Endoleak");

        Assert.assertNotNull(endoleak);
    }

}
