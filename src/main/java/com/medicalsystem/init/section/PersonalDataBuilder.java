package com.medicalsystem.init.section;

import com.medicalsystem.model.Section;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.field.IntegerField;
import com.medicalsystem.model.field.TextField;
import com.medicalsystem.service.FieldService;
import com.medicalsystem.service.SectionService;
import com.medicalsystem.util.MapUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonalDataBuilder implements SectionBuilder {

    private final FieldService fieldService;
    private final SectionService sectionService;

    @Override
    public Section build() {
        // Personal data section
        Section personalData = new Section("Personal data");

        // 1: Last name
        Field lastName = new TextField("Nazwisko", 1, null);

        // 2: First name
        Field firstName = new TextField("Imię", 2, null);

        // 3: Sex
        Field sex = new TextField("Płeć", 3, MapUtils.of("M", "M", "K", "K"));

        // 4: Age
        Field age = new IntegerField("Wiek", 4, null);


        List<Field> fields = Arrays.asList(lastName, firstName, sex, age);

        fields.forEach(field -> {
            personalData.addField(field);
            fieldService.saveOrUpdate(field);
        });

        sectionService.saveOrUpdate(personalData);

        return personalData;
    }

}
