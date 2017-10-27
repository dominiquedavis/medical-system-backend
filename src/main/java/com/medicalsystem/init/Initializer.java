package com.medicalsystem.init;

import com.medicalsystem.properties.FormProperties;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class Initializer {

    private final FormProperties formProperties;

    public void runInitialConfiguration() {

    }

}
