package com.medicalsystem.factory;

import com.medicalsystem.model.field.Field;
import com.medicalsystem.properties.ConfigProperties;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log
public class FieldFactory {

    private static final String fieldPackage = "com.medicalsystem.model.field";

    /**
     * Creates a Field object from the config file
     * @param _field an object representing field loaded from config
     * @return a Field object
     */
    public static Field<?> fromConfig(ConfigProperties.Form.Section.Field _field) {
        Field<?> field = getFieldObject(_field.getType());

        // Set name
        field.setName(_field.getName());

        // Set excel column
        field.setExcelColumn(_field.getIndex());

        // Add options
        List<ConfigProperties.Form.Section.Field.Option> options = _field.getOptions();
        if (options != null) {
            options.forEach(option -> field.addOption(option.getKey(), option.getVal()));
        }

        return field;
    }

    /**
     * Creates a Field object upon the type of the class
     *
     * @param type type of the class, e.g. "Text" or "Date"
     * @return a proper extension of the Field class, eg. "TextField" or "DateField"
     */
    private static Field<?> getFieldObject(String type) {
        Field<?> field = null;
        String className = fieldPackage + "." + type + "Field";
        try {
            Class<?> c = Class.forName(className);
            field = (Field<?>) c.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            log.severe(e.getMessage());
        }
        return field;
    }

}
