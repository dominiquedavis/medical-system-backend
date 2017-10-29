package com.medicalsystem.controller;

import com.medicalsystem.model.json.JSONField;
import com.medicalsystem.model.json.JSONForm;
import com.medicalsystem.model.json.JSONSection;
import com.medicalsystem.service.FormService;
import com.medicalsystem.service.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("api/forms")
public class FormController {

    private final FormService formService;
    private final SectionService sectionService;

    @GetMapping
    public List<JSONForm> getForms() {
        return formService.getAllAsJSON();
    }

    @PostMapping("{formId}/sections")
    public void addSection(@PathVariable long formId, @RequestBody JSONSection jsonSection) {
        formService.addSection(formId, jsonSection);
    }

    @PostMapping("{formId}/sections/{sectionId}")
    public void addField(@PathVariable long formId, @PathVariable long sectionId, @RequestBody JSONField jsonField) {
        sectionService.addField(sectionId, jsonField);
    }
}
