package com.medicalsystem.service;

import com.medicalsystem.json.model.JSONSection;
import com.medicalsystem.model.Form;
import com.medicalsystem.model.Section;

public interface SectionService extends CRUDService<Section, Integer> {

    Section findByNameAndForm(String name, Form form);

    void addSection(int formId, JSONSection jsonSection);

}
