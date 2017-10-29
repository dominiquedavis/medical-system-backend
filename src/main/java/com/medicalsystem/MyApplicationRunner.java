package com.medicalsystem;

import com.medicalsystem.excel.importer.ExcelImporter;
import com.medicalsystem.init.Initializer;
import com.medicalsystem.model.User;
import com.medicalsystem.service.UserService;
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
    private final UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Running initialization...");

        // Create admin account
        createAdminAccount();

        // Create forms, sections and field from properties file
        initializer.runInitialConfiguration();

        // Run import
        excelImporter.importToDatabase(new FileInputStream("data/baza2.xlsx"), 10);
    }

    private void createAdminAccount() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setAdmin(true);
        userService.register(user);
    }
}
