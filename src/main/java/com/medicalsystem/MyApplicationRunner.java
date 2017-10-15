package com.medicalsystem;

import com.medicalsystem.importer.DataImporter;
import com.medicalsystem.init.Initializer;
import com.medicalsystem.service.FieldValueService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class MyApplicationRunner implements ApplicationRunner {

    private final Initializer initializer;
    private final DataImporter dataImporter;
    private final FieldValueService fieldValueService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Running initialization...");
        initializer.prepareInitialConfiguration();
        dataImporter.importToDatabase(new FileInputStream("data/baza2.xlsx"));

        System.out.println(fieldValueService.findAll().size());
    }
}
