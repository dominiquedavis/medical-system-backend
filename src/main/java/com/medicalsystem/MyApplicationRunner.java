package com.medicalsystem;

import com.medicalsystem.model.FormType;
import com.medicalsystem.model.Patient;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.field.TextField;
import com.medicalsystem.model.value.FieldValue;
import com.medicalsystem.model.value.TextFieldValue;
import com.medicalsystem.service.FieldService;
import com.medicalsystem.service.FieldValueService;
import com.medicalsystem.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class MyApplicationRunner implements ApplicationRunner {

    private final FieldService fieldService;
    private final FieldValueService fieldValueService;
    private final PatientService patientService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Running initialization...");

        TextField field = new TextField();
        field.setName("Field");
        field.setEvarExcelColumn(1);
        field.setOpenExcelColumn(2);

        Field f = fieldService.saveOrUpdate(field);

        Patient patient = new Patient();
        patient.setId(1);
        patient.setFormType(FormType.OPEN);
        patientService.saveOrUpdate(patient);

        TextFieldValue fieldValue = new TextFieldValue();
        fieldValue.setField(field);
        fieldValue.setValue("jakaś tam");
        fieldValue.setPatient(patient);

        FieldValue fv = fieldValueService.saveOrUpdate(fieldValue);
        System.out.println(fv.getId());

        List<FieldValue<? extends Field, ?>> fieldValues = fieldValueService.findAll();
        System.out.println(fieldValues.size());

        fieldValueService.deleteById(fv.getId());
        System.out.println(fieldValueService.findAll().size());
    }
}
