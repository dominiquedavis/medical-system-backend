package com.medicalsystem;

import com.medicalsystem.model.field.StringField;
import com.medicalsystem.service.StringFieldService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Log
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Initializer implements ApplicationRunner {

    private final StringFieldService stringFieldService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Running initialization...");

        Map<String, String> smokingValues = new HashMap<>();
        smokingValues.put("0", "niepalący");
        smokingValues.put("1", "palący");
        smokingValues.put("2", "palący w przeszłości");
        smokingValues.put("3", "brak danych");

        StringField smokingField = new StringField();
        smokingField.setName("Smoking");
        smokingField.setExcelColumn(17);
        smokingField.setOptions(smokingValues);

        stringFieldService.saveOrUpdate(smokingField);
    }
}
