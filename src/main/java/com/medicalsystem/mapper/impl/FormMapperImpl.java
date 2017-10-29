package com.medicalsystem.mapper.impl;

import com.medicalsystem.mapper.FormMapper;
import com.medicalsystem.mapper.SectionMapper;
import com.medicalsystem.model.Form;
import com.medicalsystem.model.json.JSONForm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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
}
