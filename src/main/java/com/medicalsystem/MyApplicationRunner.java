package com.medicalsystem;

import com.medicalsystem.excel.exporter.ExcelExporter;
import com.medicalsystem.excel.importer.ExcelImporter;
import com.medicalsystem.init.Initializer;
import com.medicalsystem.model.ApplicationUser;
import com.medicalsystem.service.ApplicationUserService;
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
    private final ExcelExporter excelExporter;
    private final ApplicationUserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Running initialization...");

        // Create accounts
        createAccount("admin", "admin", true);
        createAccount("user", "user", false);

        // Create forms, sections and field from properties file
        //initializer.runInitialConfiguration();

        // Run import
        //excelImporter.importToDatabase("data/baza2.xlsx", 10);

        // Run export
        //excelExporter.exportToFile("data/exported.xlsx");
    }

    private void createAccount(String username, String password, boolean admin) {
        ApplicationUser user = new ApplicationUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setAdmin(admin);
        userService.register(user);
    }
}
