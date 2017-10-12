package com.medicalsystem.init.section;

import com.medicalsystem.factory.FieldFactory;
import com.medicalsystem.model.Section;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.field.TextField;
import com.medicalsystem.properties.StringProperties;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class PersonalDataBuilder implements SectionBuilder {

    private final StringProperties props;

    @Override
    public Section build() {
        Section section = new Section(props.get("section.personaldata"));

        Field lastName = FieldFactory.create(TextField::new, "field.lastname.name", "field.lastname.index", null);
        Field firstName = FieldFactory.create(TextField::new, "field.firstname.name", "field.firstname.index", null);
        Field sex = FieldFactory.create(TextField::new, "field.sex.name", "field.sex.index", null);

        log.info(String.format("Section created: '%s'", section.getName()));

        return section;
    }
}
