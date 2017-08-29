package com.medicalsystem.controller;

import com.medicalsystem.model.Form;
import com.medicalsystem.service.FormService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FormController {

    private final FormService formService;

    @GetMapping("api/patients/{patientId}/form")
    public Form getForm(@PathVariable int patientId) {
        Form form = formService.findAll().get(0);
        System.out.println(form.getSections().size());
        return form;
    }

}
