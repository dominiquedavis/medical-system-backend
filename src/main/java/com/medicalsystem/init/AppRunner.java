package com.medicalsystem.init;

import com.medicalsystem.model.Section;
import com.medicalsystem.service.SectionService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class AppRunner implements ApplicationRunner {

    private final FormInitializer initializer;

    private final SectionService sectionService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Running initialization...");

        initializer.execute();


        Section section = sectionService.findById(1);
        System.out.println("SECTION NAME:   " + section.getName());
        System.out.println("SECTION FIELDS: " + section.getFields().size());

    }
}
