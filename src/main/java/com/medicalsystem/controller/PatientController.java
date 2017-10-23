package com.medicalsystem.controller;

import com.medicalsystem.json.model.JSONForm;
import com.medicalsystem.service.FormService;
import com.medicalsystem.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PatientController {

    private final PatientService patientService;
    private final FormService formService;

    @GetMapping("api/patients/{patientId}")
    public boolean checkId(@PathVariable int patientId) {
        return patientService.exists(patientId);
    }

    @PostMapping("api/patients/{patientId}")
    public boolean createPatient(@PathVariable int patientId) {
        return patientService.createPatient(patientId);
    }

    @GetMapping("api/patients/{patientId}/patientForm")
    public JSONForm getForm(@PathVariable int patientId) {
        return formService.getForm(patientId);
    }

    @PutMapping("api/patients/{patientId}/patientForm")
    public JSONForm updateForm(@PathVariable int patientId, JSONForm jsonForm) {
        return formService.updateForm(patientId, jsonForm);
    }

}
