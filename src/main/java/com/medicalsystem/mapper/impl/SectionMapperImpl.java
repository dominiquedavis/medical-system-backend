package com.medicalsystem.mapper.impl;

import com.medicalsystem.mapper.FieldMapper;
import com.medicalsystem.mapper.SectionMapper;
import com.medicalsystem.model.Field;
import com.medicalsystem.model.Patient;
import com.medicalsystem.model.Section;
import com.medicalsystem.model.json.JSONSection;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SectionMapperImpl implements SectionMapper {

    private final FieldMapper fieldMapper;

    @Override
    public List<JSONSection> toJSONs(List<Section> sections) {
        return sections.stream().map(this::toJSON).collect(Collectors.toList());
    }

    @Override
    public JSONSection toJSON(Section section) {
        return JSONSection.builder()
                .id(section.getId())
                .name(section.getName())
                .fields(fieldMapper.toJSONs(section.getFields()))
                .build();
    }

    @Override
    public Section fromJSON(JSONSection jsonSection) {
        Section section = new Section();
        section.setId(jsonSection.getId());
        section.setName(jsonSection.getName());
        return section;
    }

    @Override
    public List<JSONSection> toJSONs(List<Section> sections, Patient patient) {
        return sections.stream().map(section -> toJSON(section, patient)).collect(Collectors.toList());
    }

    @Override
    public JSONSection toJSON(Section section, Patient patient) {
        System.out.println("Section: " + section.getName() + " size: " + section.getFields().size());
        // TODO:
        List<Field> fields = new ArrayList<>(new HashSet<>(section.getFields()));
        fields.sort(Comparator.comparingLong(Field::getId));

        return JSONSection.builder()
                .id(section.getId())
                .name(section.getName())
                .fields(fieldMapper.toJSONs(fields, patient))
                .build();
    }
}
