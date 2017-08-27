package com.medicalsystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FormController {

    @GetMapping("api/patients/{patientId}/form")
    public ResponseEntity<?> getForm(@PathVariable int patientId) {
        return null;
    }

}
