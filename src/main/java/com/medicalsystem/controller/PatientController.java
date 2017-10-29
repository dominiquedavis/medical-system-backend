package com.medicalsystem.controller;

import com.medicalsystem.model.json.JSONForm;
import com.medicalsystem.service.FormService;
import com.medicalsystem.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("api/patients")
public class PatientController {

    private final PatientService patientService;
    private final FormService formService;

    @GetMapping("{patientId}")
    public boolean exists(@PathVariable long patientId) {
        return patientService.existsById(patientId);
    }

    // TODO: Potrzebna dodatkowa informacja o formie pacjenta
    @PostMapping("{patientId}")
    public boolean createPatient(@PathVariable long patientId) {
        return patientService.create(patientId);
    }

    @GetMapping("{patientId}/patientForm")
    public JSONForm getForm(@PathVariable long patientId) {
        return formService.getJSONFormByPatientId(patientId);
    }
}
