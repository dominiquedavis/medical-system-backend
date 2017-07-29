package com.medicalsystem;

import com.medicalsystem.model.Section;
import com.medicalsystem.model.field.DateField;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.field.StringField;
import com.medicalsystem.repository.FieldRepository;
import com.medicalsystem.repository.SectionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Log
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Initializer implements ApplicationRunner {

    private final SectionRepository sectionRepository;
    private final FieldRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Running initialization...");

        Map<String, String> smokingValues = new HashMap<>();
        smokingValues.put("0", "niepalący");
        smokingValues.put("1", "palący");
        smokingValues.put("2", "palący w przeszłości");
        smokingValues.put("3", "brak danych");

        StringField smokingField = new StringField();
        smokingField.setName("Smoking");
        smokingField.setExcelColumn(17);
        smokingField.setOptions(smokingValues);

        // ------------------------------------------------------------
        Map<String, String> values = new HashMap<>();
        values.put("0", "cośtam");
        values.put("1", "dupa");
        values.put("2", "2dupa");

        StringField someField = new StringField();
        someField.setName("Some field");
        someField.setExcelColumn(41);
        someField.setOptions(values);

        // ------------------------------------------------------------
        Map<String, Date> dateValues = new HashMap<>();
        dateValues.put("0", new Date());
        dateValues.put("1", new Date());
        dateValues.put("2", new Date());

        DateField dateField = new DateField();
        dateField.setName("Pole datowe");
        dateField.setExcelColumn(6);
        dateField.setOptions(dateValues);

        //-------------------------------------------------------------
        Section personalData = new Section();
        personalData.setName("Personal data");

        Section operationSection = new Section();
        operationSection.setName("Operation");

        sectionRepository.save(personalData);
        sectionRepository.save(operationSection);

        List<Field<?>> fields = Arrays.asList(smokingField, someField, dateField);
        personalData.setFields(fields);

        smokingField.setSections(Arrays.asList(personalData, operationSection));
        someField.setSections(Arrays.asList(personalData));
        dateField.setSections(Arrays.asList(personalData));

        sectionRepository.save(personalData);
        sectionRepository.save(operationSection);

        repository.save(smokingField);
        repository.save(someField);
        repository.save(dateField);

        List<Section> sections = sectionRepository.findAll();
        sections.forEach(section -> {
            System.out.println("Section name: " + section.getName());
            System.out.println("Section fields: " + section.getFields().stream().map(Field::getName).collect(Collectors.toList()));
        });
    }
}
