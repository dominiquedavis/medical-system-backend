package com.medicalsystem.factory;

import com.medicalsystem.model.Form;
import com.medicalsystem.model.Section;
import com.medicalsystem.properties.ConfigProperties;
import com.medicalsystem.service.FormService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Component
@Log
public class FormFactory {

    private static ConfigProperties props;
    private static FormService formService;

    @Autowired
    public FormFactory(ConfigProperties props, FormService formService) {
        FormFactory.props = props;
        FormFactory.formService = formService;
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

        // Create and add sections
        _open.getSections().forEach(_section -> {
            Section section = SectionFactory.fromConfig(_section);
            open.addSection(section);
        });

        // Persist created form
        formService.saveOrUpdate(open);

        log.info(String.format("Form created: '%s'", open.getName()));

        return open;
    }

    public static Form evar() {
        // TODO: Implement
        throw new NotImplementedException();
    }

}
