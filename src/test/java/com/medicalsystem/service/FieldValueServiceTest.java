package com.medicalsystem.service;

import com.medicalsystem.model.field.TextField;
import com.medicalsystem.model.value.FieldValue;
import com.medicalsystem.model.value.TextFieldValue;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FieldValueServiceTest {

    @Autowired
    private FieldService fieldService;

    @Autowired
    private FieldValueService fieldValueService;

    @Test
    public void shouldPersistFieldValuesCorrectly() {
        TextField field = new TextField();
        field.setName("Jakieś pole");
        field.setExcelColumn(1);

        TextFieldValue fieldValue = new TextFieldValue();
        fieldValue.setPatientId(13);
        fieldValue.setField(field);
        fieldValue.setValues(Arrays.asList("abc", "def"));

        fieldService.saveOrUpdate(field);
        fieldValueService.saveOrUpdate(fieldValue);

        FieldValue<?> queriedValue = fieldValueService.findByFieldAndPatientId(field, 13);

        Assert.assertNotNull(queriedValue);
    }

}
