package com.medicalsystem.controller;

import com.medicalsystem.json.model.JSONField;
import com.medicalsystem.json.model.JSONForm;
import com.medicalsystem.json.model.JSONSection;
import com.medicalsystem.service.FieldService;
import com.medicalsystem.service.FormService;
import com.medicalsystem.service.SectionService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class FormController {

    private final FormService formService;
    private final SectionService sectionService;
    private final FieldService fieldService;

    @GetMapping("api/forms")
    public List<JSONForm> getForms() {
        return formService.getForms();
    }

    @PostMapping("api/forms/{formId}/sections")
    public void addSection(@PathVariable int formId, JSONSection jsonSection) {
        sectionService.addSection(formId, jsonSection);
    }

    @PostMapping("api/forms/{formId}/sections/{sectionId}")
    public void addField(@PathVariable int formId, @PathVariable int sectionId, JSONField jsonField) {
        fieldService.addField(formId, sectionId, jsonField);
    }

}
