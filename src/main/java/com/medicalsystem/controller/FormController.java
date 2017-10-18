package com.medicalsystem.controller;

import com.medicalsystem.model.Form;
import com.medicalsystem.model.Section;
import com.medicalsystem.service.FormService;
import com.medicalsystem.service.SectionService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class FormController {

    private final FormService formService;
    private final SectionService sectionService;

    /**
     * Returns all forms as patterns (values and possible values are null)
     *
     * @return all forms
     */
    @GetMapping("api/forms")
    public List<Form> getForms() {
        return formService.findAll();
    }

    /**
     * Adds section to the given form
     *
     * @param formId  ID of the form to add the section
     * @param section a Section object
     */
    @PostMapping("api/forms/{formId}/sections")
    public void addSection(@PathVariable int formId, @RequestBody Section section) {
        // TODO: check if section name exists for given form
        Form form = formService.findById(formId);
        section.setForm(form);
        sectionService.saveOrUpdate(section);
    }

}
