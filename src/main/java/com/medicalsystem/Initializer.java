package com.medicalsystem;

import com.medicalsystem.service.FieldService;
import com.medicalsystem.service.SectionService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Log
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Initializer implements ApplicationRunner {

    private final SectionService sectionService;
    private final FieldService fieldService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Running initialization...");
    }
}
