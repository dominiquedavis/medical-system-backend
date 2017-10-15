package com.medicalsystem.factory;

import com.medicalsystem.model.Section;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.properties.ConfigProperties;
import com.medicalsystem.service.SectionService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log
public class SectionFactory {

    private static SectionService sectionService;

    @Autowired
    public SectionFactory(SectionService sectionService) {
        SectionFactory.sectionService = sectionService;
    }

    /**
     * Creates a Section object from the config file
     *
     * @param _section an object representing a section loaded from config
     * @return         created Section object
     */
    public static Section fromConfig(ConfigProperties.Form.Section _section) {
        Section section = new Section();

        // Set name
        section.setName(_section.getName());

        // Create and add fields
        _section.getFields().forEach(_field -> {
            Field field = FieldFactory.fromConfig(_field);
            section.addField(field);
        });

        // Persist created section
        sectionService.saveOrUpdate(section);

        log.info(String.format("Section created: '%s'", section.getName()));

        return section;
    }

}
