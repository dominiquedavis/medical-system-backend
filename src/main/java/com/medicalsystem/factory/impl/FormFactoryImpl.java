package com.medicalsystem.factory.impl;

import com.medicalsystem.factory.FormFactory;
import com.medicalsystem.factory.SectionFactory;
import com.medicalsystem.model.Form;
import com.medicalsystem.properties.FormProperties;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FormFactoryImpl implements FormFactory {

    private final SectionFactory sectionFactory;

    @Override
    public List<Form> fromProperties(List<FormProperties.Form> propertiesForms) {
        if (propertiesForms == null)
            return new ArrayList<>();

        return propertiesForms.stream()
                .map(this::fromProperties)
                .collect(Collectors.toList());
    }

    @Override
    public Form fromProperties(FormProperties.Form propertiesForm) {
        if (propertiesForm == null)
            return null;

        Form form = new Form();
        form.setName(propertiesForm.getName());
        form.setExcelSheetIndex(propertiesForm.getIndex());
        form.setSections(sectionFactory.fromProperties(propertiesForm.getSections()));
        form.getSections().forEach(section -> section.setForm(form));
        return form;
    }
}
