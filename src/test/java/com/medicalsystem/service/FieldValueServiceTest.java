package com.medicalsystem.service;

import com.medicalsystem.model.FormType;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.FieldValue;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FieldValueServiceTest {

    @Autowired
    private FieldService fieldService;

    @Autowired
    private FieldValueService fieldValueService;

    @Test
    public void fieldValueServiceShouldWorkCorretly() {
        int patientId = 1;
        List<FieldValue<?>> values = fieldValueService.findAllByPatientId(patientId);

        Assert.assertNotNull(values);

        System.out.println(values.size());

        values.stream()
                .map(FieldValue::getField)
                .sorted(Comparator.comparingInt(Field::getOpenExcelColumn))
                .map(field -> field.getOpenExcelColumn() + " " + field.getName())
                .forEach(System.out::println);

        List<Field<?>> fields = fieldService.findAllByFormType(FormType.EVAR);
        System.out.println(fields.size());
    }

}
