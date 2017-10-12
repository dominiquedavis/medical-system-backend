package com.medicalsystem.init.section;

import com.medicalsystem.factory.FieldFactory;
import com.medicalsystem.model.Section;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.field.TextField;
import com.medicalsystem.properties.StringProperties;
import com.medicalsystem.service.FieldService;
import com.medicalsystem.service.SectionService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class PersonalDataBuilder implements SectionBuilder {

    private final SectionService sectionService;
    private final FieldService fieldService;
    private final StringProperties props;

    @Override
    public Section build() {
        Section section = new Section(props.get("section.personaldata"));

        for (Field<?> field : createFields()) {
            section.addField(field);
            fieldService.saveOrUpdate(field);
            log.info(String.format("Field created: '%s'", field.getName()));
        }

        sectionService.saveOrUpdate(section);
        log.info(String.format("Section created: '%s'", section.getName()));

        return section;
    }

    private List<Field<?>> createFields() {
        return Arrays.asList(
                FieldFactory.create(TextField::new, "field.lastname.name", "field.lastname.index", null),
                FieldFactory.create(TextField::new, "field.firstname.name", "field.firstname.index", null),
                FieldFactory.create(TextField::new, "field.sex.name", "field.sex.index", null)
        );
    }

}
