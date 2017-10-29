package com.medicalsystem;

import com.medicalsystem.excel.importer.ExcelImporter;
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
    private final ExcelImporter excelImporter;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Running initialization...");

        // Create forms, sections and field from properties file
        //initializer.runInitialConfiguration();

        // Run import
        //excelImporter.importToDatabase(new FileInputStream("data/baza2.xlsx"), 10);
    }
}
