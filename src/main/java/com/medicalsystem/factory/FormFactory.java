package com.medicalsystem.factory;

import com.medicalsystem.model.Form;
import com.medicalsystem.model.FormType;
import com.medicalsystem.model.Section;
import com.medicalsystem.properties.ConfigProperties;
import com.medicalsystem.service.FormService;
import com.medicalsystem.service.SectionService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log
public class FormFactory {

    private static ConfigProperties props;
    private static FormService formService;
    private static SectionService sectionService;

    @Autowired
    public FormFactory(ConfigProperties props, FormService formService, SectionService sectionService) {
        FormFactory.props = props;
        FormFactory.formService = formService;
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

    /**
     * Creates and persists a Form object representing the EVAR form
     *
     * @return created Form object
     */
    public static Form createEvar() {
        return createForm(FormType.EVAR);
    }

    /**
     * Creates and persists a Form object upon form type
     *
     * @param formType either OPEN or EVAR
     * @return         a created Form object
     */
    private static Form createForm(FormType formType) {
        Form form = new Form();

        // Set name
        form.setName(formType == FormType.OPEN ? props.getOpenName() : props.getEvarName());

        // Set type
        form.setType(formType);

        // Persist incomplete form (needed for persisting section)
        formService.saveOrUpdate(form);

        // Create and add sections
        props.getSections().forEach(_section -> {
            Section section = SectionFactory.fromConfig(_section, form);
            sectionService.saveOrUpdate(section);
            form.addSection(section);
        });

        log.info(String.format("Form created: '%s'", form.getName()));

        return form;
    }

}
