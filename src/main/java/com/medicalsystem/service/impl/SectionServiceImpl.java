package com.medicalsystem.service.impl;

import com.medicalsystem.json.mapper.SectionMapper;
import com.medicalsystem.json.model.JSONSection;
import com.medicalsystem.model.Form;
import com.medicalsystem.model.Section;
import com.medicalsystem.repository.SectionRepository;
import com.medicalsystem.service.FormService;
import com.medicalsystem.service.SectionService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
@Log
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;
    private final FormService formService;

    @Override
    public List<Section> findAll() {
        return sectionRepository.findAll();
    }

    @Override
    public Section findById(Integer id) {
        return sectionRepository.findOne(id);
    }

    @Override
    public Section saveOrUpdate(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public void delete(Section section) {
        sectionRepository.delete(section);
    }

    @Override
    public void deleteById(Integer id) {
        sectionRepository.delete(id);
    }

    @Override
    public Section findByNameAndForm(String name, Form form) {
        return sectionRepository.findByNameAndForm(name, form);
    }

    @Override
    public void addSection(int formId, JSONSection jsonSection) {
        Form form = formService.findById(formId);

        if (form == null) {
            log.severe("Form not found with ID: " + formId);
            return;
        }

        jsonSection.setFields(Collections.emptyList());
        Section section = sectionMapper.fromJSON(jsonSection);

        // Persist section
        saveOrUpdate(section);

        // Update form
        form.addSection(section);
        formService.saveOrUpdate(form);

        log.info("Section added: " + section.getName());
    }

}
