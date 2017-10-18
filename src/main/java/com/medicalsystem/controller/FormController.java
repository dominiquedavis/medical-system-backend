package com.medicalsystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.medicalsystem.model.Form;
import com.medicalsystem.service.FormService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class FormController {

    private final FormService formService;

    @GetMapping("api/forms")
    public List<Form> getForms() throws JsonProcessingException {
        List<Form> forms = formService.findAll();
        return forms;
    }

}
