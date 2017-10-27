package com.medicalsystem.factory.impl;

import com.medicalsystem.factory.FieldFactory;
import com.medicalsystem.factory.SectionFactory;
import com.medicalsystem.model.Section;
import com.medicalsystem.properties.FormProperties;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SectionFactoryImpl implements SectionFactory {

    private final FieldFactory fieldFactory;

    @Override
    public List<Section> fromProperties(List<FormProperties.Form.Section> propertiesSections) {
        if (propertiesSections == null)
            return new ArrayList<>();

        return propertiesSections.stream()
                .map(this::fromProperties)
                .collect(Collectors.toList());
    }

    @Override
    public Section fromProperties(FormProperties.Form.Section propertiesSection) {
        if (propertiesSection == null)
            return null;

        Section section = new Section();
        section.setName(propertiesSection.getName());
        section.setFields(fieldFactory.fromProperties(propertiesSection.getFields()));
        section.getFields().forEach(field -> field.setSection(section));
        return section;
    }
}
