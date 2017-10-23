package com.medicalsystem.json.mapper.impl;

import com.medicalsystem.json.mapper.FieldMapper;
import com.medicalsystem.json.mapper.SectionMapper;
import com.medicalsystem.json.model.JSONField;
import com.medicalsystem.json.model.JSONSection;
import com.medicalsystem.model.Patient;
import com.medicalsystem.model.Section;
import com.medicalsystem.model.field.Field;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SectionMapperImpl implements SectionMapper {

    private final FieldMapper fieldMapper;

    @Override
    public Section fromJSON(JSONSection jsonSection) {
        Section section = new Section();

        // Set ID
        section.setId(jsonSection.getId());

        // Set name
        section.setName(jsonSection.getName());

        // Set fields
        List<Field<?>> fields = jsonSection.getFields().stream().map(fieldMapper::fromJSON).collect(Collectors.toList());
        section.setFields(fields);

        return section;
    }

    @Override
    public JSONSection toJSON(Section section) {
        return null;
    }

    @Override
    public JSONSection toJSON(Section section, int patientId) {
        JSONSection jsonSection = new JSONSection();

        // Set ID
        jsonSection.setId(section.getId());

        // Set name
        jsonSection.setName(section.getName());

        // Set fields
        List<JSONField> jsonFields = section.getFields().stream()
                .map(f -> fieldMapper.toJSON(f, patientId))
                .collect(Collectors.toList());
        jsonSection.setFields(jsonFields);

        return jsonSection;
    }
}
