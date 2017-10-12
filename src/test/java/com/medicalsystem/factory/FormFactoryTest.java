package com.medicalsystem.factory;

import com.medicalsystem.model.Form;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FormFactoryTest {

    @Test
    public void shouldCreateFormsCorrectly() {
        Form form = FormFactory.openFromConfig();

        Assert.assertNotNull(form);
    }

}
