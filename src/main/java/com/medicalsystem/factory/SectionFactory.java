package com.medicalsystem.factory;

import com.medicalsystem.model.FormType;
import com.medicalsystem.model.Section;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.properties.ConfigProperties;
import com.medicalsystem.service.FieldService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log
public class SectionFactory {

    private static FieldService fieldService;

    @Autowired
    public SectionFactory(FieldService fieldService) {
        SectionFactory.fieldService = fieldService;
    }

    /**
     * Creates a Section object from the config file
     *
     * @param _section an object representing a section loaded from config
     * @return         created Section object
     */
    public static Section fromConfig(ConfigProperties.Section _section, FormType formType) {
        Section section = new Section();

        // Set name
        section.setName(_section.getName());

        // Create and add fields
        _section.getFields().stream()
                .filter(_field -> fieldIsRelevant(_field, formType))
                .forEach(_field -> {
                    Field field = FieldFactory.fromConfig(_field);
                    fieldService.saveOrUpdate(field);
                    section.addField(field);
                });

        log.info(String.format("Section created: '%s'", section.getName()));

        return section;
    }

    private static boolean fieldIsRelevant(ConfigProperties.Section.Field field, FormType formType) {

        if (formType == FormType.OPEN)
            return field.getOpenIndex() != -1;

        if (formType == FormType.EVAR)
            return field.getEvarIndex() != -1;

        return false;
    }

}
