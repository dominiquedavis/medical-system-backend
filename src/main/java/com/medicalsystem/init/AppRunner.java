package com.medicalsystem.init;

import com.medicalsystem.model.value.FieldValue;
import com.medicalsystem.model.value.IntegerFieldValue;
import com.medicalsystem.model.value.TextFieldValue;
import com.medicalsystem.service.FieldService;
import com.medicalsystem.service.FieldValueService;
import com.medicalsystem.service.SectionService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class AppRunner implements ApplicationRunner {

    private final Initializer initializer;

    private final SectionService sectionService;
    private final FieldService fieldService;
    private final FieldValueService fieldValueService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Running initialization...");

        initializer.execute();

        // Add some fake data
        int patientId = 13;

        FieldValue<String> lastName = new TextFieldValue();
        lastName.setPatientId(patientId);
        lastName.setField(fieldService.findByName("Last name"));
        lastName.setValue("Kuchta");
        fieldValueService.saveOrUpdate(lastName);

        FieldValue<String> firstName = new TextFieldValue();
        firstName.setPatientId(patientId);
        firstName.setField(fieldService.findByName("First name"));
        firstName.setValue("Zbigniew");
        fieldValueService.saveOrUpdate(firstName);

        FieldValue<String> sex = new TextFieldValue();
        sex.setPatientId(patientId);
        sex.setField(fieldService.findByName("Sex"));
        sex.setValue("M");
        fieldValueService.saveOrUpdate(sex);

        FieldValue<Integer> age = new IntegerFieldValue();
        age.setPatientId(patientId);
        age.setField(fieldService.findByName("Age"));
        age.setValue(65);
        fieldValueService.saveOrUpdate(age);
    }
}
