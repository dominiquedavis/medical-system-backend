package com.medicalsystem.init;

import com.medicalsystem.init.section.SectionBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class DefaultFormInitializer implements FormInitializer {

    private final List<? extends SectionBuilder> builders;

    @Override
    public void execute() {
        builders.forEach(SectionBuilder::build);
    }
}
