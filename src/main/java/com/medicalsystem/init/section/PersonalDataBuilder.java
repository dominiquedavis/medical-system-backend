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

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonalDataBuilder implements SectionBuilder {

    private final FieldService fieldService;
    private final SectionService sectionService;

    @Override
    public Section build() {
        // Personal data section
        Section personalData = new Section("Personal data");

        // Last name
        Field lastName = new TextField("Nazwisko", 1, null);

        // First name
        Field firstName = new TextField("Imię", 2, null);

        // Sex
        Field sex = new TextField("Płeć", 3, MapUtils.ofStrings("M", "M", "K", "K"));

        // Age
        Field age = new IntegerField("Wiek", 4, null);

        personalData.addField(lastName);
        personalData.addField(firstName);
        personalData.addField(sex);
        personalData.addField(age);

        fieldService.saveOrUpdate(lastName);
        fieldService.saveOrUpdate(firstName);
        fieldService.saveOrUpdate(sex);
        fieldService.saveOrUpdate(age);

        sectionService.saveOrUpdate(personalData);

        return personalData;
    }

}
