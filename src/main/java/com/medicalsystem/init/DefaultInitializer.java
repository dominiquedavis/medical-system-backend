package com.medicalsystem.init;

import com.medicalsystem.factory.FormFactory;
import com.medicalsystem.model.Form;
import com.medicalsystem.service.FormService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class DefaultInitializer implements Initializer {

    private final FormService formService;

    @Override
    public void prepareInitialConfiguration() {

        Form openForm = FormFactory.createOpen();
        Form evarForm = FormFactory.createEvar();

        formService.saveOrUpdate(openForm);
        formService.saveOrUpdate(evarForm);

    }

}
