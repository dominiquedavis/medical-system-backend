package com.medicalsystem.mapper.impl;

import com.medicalsystem.mapper.FormMapper;
import com.medicalsystem.mapper.SectionMapper;
import com.medicalsystem.model.Form;
import com.medicalsystem.model.Patient;
import com.medicalsystem.model.Section;
import com.medicalsystem.model.json.JSONForm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FormMapperImpl implements FormMapper {

    private final SectionMapper sectionMapper;

    @Override
    public List<JSONForm> toJSONs(List<Form> forms) {
        return forms.stream().map(this::toJSON).collect(Collectors.toList());
    }

    @Override
    public JSONForm toJSON(Form form) {
        return JSONForm.builder()
                .id(form.getId())
                .type(form.getType())
                .sections(sectionMapper.toJSONs(form.getSections()))
                .build();
    }

    @Override
    public Form fromJSON(JSONForm jsonForm) {
        return null;
    }

    @Override
    public List<JSONForm> toJSONs(List<Form> forms, Patient patient) {
        return forms.stream().map(form -> toJSON(form, patient)).collect(Collectors.toList());
    }

    @Override
    public JSONForm toJSON(Form form, Patient patient) {
        System.out.println("Form: " + form.getName() + " size: " + form.getSections().size());
        // TODO:
        List<Section> sections = new ArrayList<>(new HashSet<>(form.getSections()));
        sections.sort(Comparator.comparingLong(Section::getId));

        return JSONForm.builder()
                .id(form.getId())
                .type(form.getType())
                .sections(sectionMapper.toJSONs(sections, patient))
                .build();
    }
}
