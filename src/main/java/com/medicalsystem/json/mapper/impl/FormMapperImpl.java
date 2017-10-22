package com.medicalsystem.json.mapper.impl;

import com.medicalsystem.json.mapper.FormMapper;
import com.medicalsystem.json.mapper.SectionMapper;
import com.medicalsystem.json.model.JSONForm;
import com.medicalsystem.model.Form;
import com.medicalsystem.model.FormType;
import com.medicalsystem.model.Section;
import com.medicalsystem.properties.ConfigProperties;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FormMapperImpl implements FormMapper {

    private final SectionMapper sectionMapper;
    private final ConfigProperties props;

    @Override
    public Form fromJSON(JSONForm jsonForm) {
        Form form = new Form();

        // Set ID
        form.setId(jsonForm.getId());

        // Set type
        FormType formType = FormType.valueOf(jsonForm.getType());
        form.setType(formType);

        // Set name
        form.setName(formType == FormType.OPEN ? props.getOpenName() : props.getEvarName());

        // Set sections
        List<Section> sections = jsonForm.getSections().stream().map(sectionMapper::fromJSON).collect(Collectors.toList());
        sections.forEach(section -> section.setForm(form));
        form.setSections(sections);

        return form;
    }

    @Override
    public JSONForm toJSON(Form form) {
        return null;
    }

}
