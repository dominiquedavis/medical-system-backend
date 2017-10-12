package com.medicalsystem.init;

import com.medicalsystem.init.section.SectionBuilder;
import com.medicalsystem.model.Form;
import com.medicalsystem.model.Section;
import com.medicalsystem.properties.StringProperties;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultInitializer implements Initializer {

    /**
     * An autowired list containing all section builders
     */
    private final List<? extends SectionBuilder> sectionBuilders;

    private final StringProperties props;

    @Override
    public void prepareInitialConfiguration() {

        // TODO: 1. Create OPEN form with proper sections and fields
        Form openForm = createOpenForm();

        // TODO: 2. Create EVAR form using the same sections and fields

        // TODO: 3. Remove irrelevant fields from EVAR and add the missing one

    }

    private Form createOpenForm() {
        Form form = new Form(props.get("form.open"));

        for (SectionBuilder sectionBuilder : sectionBuilders) {
            Section section = sectionBuilder.build();
        }

        log.info(String.format("Form created: '%s'", form.getName()));

        return form;
    }

}
