package com.medicalsystem;

import com.medicalsystem.init.Initializer;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class MyApplicationRunner implements ApplicationRunner {

    private final Initializer initializer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Running initialization...");
        //initializer.prepareInitialConfiguration();
    }
}
