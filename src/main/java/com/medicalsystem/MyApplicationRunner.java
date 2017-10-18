package com.medicalsystem;

import com.medicalsystem.importer.DataImporter;
import com.medicalsystem.init.Initializer;
import com.medicalsystem.model.ApplicationUser;
import com.medicalsystem.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class MyApplicationRunner implements ApplicationRunner {

    private final Initializer initializer;
    private final DataImporter dataImporter;
    private final ApplicationUserService applicationUserService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Running initialization...");

        // Add testing user account
        ApplicationUser testingAccount = new ApplicationUser();
        testingAccount.setUsername("admin");
        testingAccount.setPassword(passwordEncoder.encode("admin"));
        testingAccount.setAdmin(true);
        applicationUserService.saveOrUpdate(testingAccount);

        // Create fields
        initializer.prepareInitialConfiguration();

        // Run import
        dataImporter.importToDatabase(new FileInputStream("data/baza2.xlsx"));
    }
}
