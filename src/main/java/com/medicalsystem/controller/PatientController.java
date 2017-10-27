package com.medicalsystem.controller;

import com.medicalsystem.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("api/patients")
public class PatientController {

    private final PatientService patientService;

    @GetMapping("{patientId}")
    public boolean exists(@PathVariable long patientId) {
        return patientService.existsById(patientId);
    }
}
