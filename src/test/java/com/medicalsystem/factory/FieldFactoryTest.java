package com.medicalsystem.factory;

import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.field.TextField;
import com.medicalsystem.properties.ConfigProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FieldFactoryTest {

    @Autowired
    private ConfigProperties props;

    @Test
    public void shouldCreateFieldsCorrectly() {
        ConfigProperties.Form.Section.Field _field = props.getOpen().getSections().get(0).getFields().get(2);
        Field field = FieldFactory.fromConfig(_field);

        Assert.assertNotNull(field);
        Assert.assertTrue(field instanceof TextField);
        Assert.assertTrue(field.getOptions().size() == 2);
    }

}
