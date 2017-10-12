package com.medicalsystem.init;

import com.medicalsystem.factory.FormFactory;
import com.medicalsystem.model.Form;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Component
@Log
public class DefaultInitializer implements Initializer {

    @Override
    public void prepareInitialConfiguration() {

        // TODO: 1. Create OPEN form with proper sections and fields
        Form openForm = FormFactory.openFromConfig();

        // TODO: 2. Create EVAR form using the same sections and fields

        // TODO: 3. Remove irrelevant fields from EVAR and add the missing one

    }

}
