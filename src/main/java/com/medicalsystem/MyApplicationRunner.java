package com.medicalsystem;

import com.medicalsystem.importer.DataImporter;
import com.medicalsystem.init.Initializer;
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

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Running initialization...");

        // Create fields
        initializer.prepareInitialConfiguration();

        // Run import
        dataImporter.importToDatabase(new FileInputStream("data/baza2.xlsx"));
    }
}
