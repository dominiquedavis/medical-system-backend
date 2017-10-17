package com.medicalsystem.factory;

import com.medicalsystem.model.Form;
import com.medicalsystem.model.FormType;
import com.medicalsystem.model.Section;
import com.medicalsystem.properties.ConfigProperties;
import com.medicalsystem.service.SectionService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log
public class FormFactory {

    private static ConfigProperties props;
    private static SectionService sectionService;

    @Autowired
    public FormFactory(ConfigProperties props, SectionService sectionService) {
        FormFactory.props = props;
        FormFactory.sectionService = sectionService;
    }

    /**
     * Creates a Form object representing the OPEN form
     *
     * @return created Form object
     */
    public static Form createOpen() {
        return createForm(FormType.OPEN);
    }

    public static Form createEvar() {
        return createForm(FormType.EVAR);
    }

    private static Form createForm(FormType formType) {
        Form form = new Form();

        // Set name
        form.setName(formType == FormType.OPEN ? props.getOpenName() : props.getEvarName());

        // Create and add sections
        props.getSections().forEach(_section -> {

            // Check if section was already created
            // If not, create and persist
            Section section = sectionService.findByName(_section.getName());

            if (section == null) {
                section = SectionFactory.fromConfig(_section, formType);
                sectionService.saveOrUpdate(section);
            }

            form.addSection(section);
        });

        log.info(String.format("Form created: '%s'", form.getName()));

        return form;
    }

}
