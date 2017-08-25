package com.medicalsystem.init;

import com.medicalsystem.init.form.FormBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class DefaultInitializer implements Initializer {

    private final List<? extends FormBuilder> formBuilders;

    @Override
    public void execute() {
        formBuilders.forEach(FormBuilder::build);
    }
}
