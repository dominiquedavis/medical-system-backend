package com.medicalsystem.factory;

import com.medicalsystem.model.Section;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.properties.ConfigProperties;
import lombok.extern.java.Log;

@Log
public class SectionFactory {

    /**
     * Creates a Section object from the config file
     * @param _section an object representing a section loaded from config
     * @return created Section object
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

        log.info(String.format("Section created: '%s'", section.getName()));

        return section;
    }

}
