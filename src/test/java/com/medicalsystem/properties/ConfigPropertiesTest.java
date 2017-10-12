package com.medicalsystem.properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigPropertiesTest {

    @Autowired
    private ConfigProperties configProperties;

    @Test
    public void propertiesShouldLoadCorrectly() {
        ConfigProperties.Form open = configProperties.getOpen();
        ConfigProperties.Form evar = configProperties.getEvar();

        Assert.assertNotNull(open);
        Assert.assertNotNull(evar);
    }

}
