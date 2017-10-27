package com.medicalsystem.init;

import com.medicalsystem.factory.FormFactory;
import com.medicalsystem.model.Form;
import com.medicalsystem.properties.FormProperties;
import com.medicalsystem.service.FormService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class InitializerImpl implements Initializer {

    private final FormProperties formProperties;
    private final FormFactory formFactory;
    private final FormService formService;

    @Override
    public void runInitialConfiguration() {
        List<Form> forms = formFactory.fromProperties(formProperties.getForms());
        forms.forEach(formService::save);
    }
}
