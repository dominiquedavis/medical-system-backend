package com.medicalsystem.factory;

import com.medicalsystem.model.Form;
import com.medicalsystem.model.Section;
import com.medicalsystem.properties.ConfigProperties;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log
public class FormFactory {

    private static ConfigProperties props;

    @Autowired
    public FormFactory(ConfigProperties props) {
        FormFactory.props = props;
    }

    /**
     * Creates a Form object representing the OPEN form
     * @return created Form object
     */
    public static Form openFromConfig() {
        ConfigProperties.Form _open = props.getOpen();
        Form open = new Form();

        // Set name
        open.setName(_open.getName());

        // Add sections
        _open.getSections().forEach(_section -> {
            Section section = SectionFactory.fromConfig(_section);
            open.addSection(section);
        });

        log.info(String.format("Form created: '%s'", open.getName()));

        return open;
    }

}
