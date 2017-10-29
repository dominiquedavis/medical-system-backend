package com.medicalsystem.service.impl;

import com.medicalsystem.mapper.FormMapper;
import com.medicalsystem.mapper.SectionMapper;
import com.medicalsystem.model.Form;
import com.medicalsystem.model.Section;
import com.medicalsystem.model.json.JSONField;
import com.medicalsystem.model.json.JSONForm;
import com.medicalsystem.model.json.JSONSection;
import com.medicalsystem.repository.FormRepository;
import com.medicalsystem.service.FormService;
import com.medicalsystem.service.SectionService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class FormServiceImpl implements FormService {

    private final FormRepository formRepository;
    private final FormMapper formMapper;
    private final SectionMapper sectionMapper;
    private final SectionService sectionService;

    @Override
    public List<Form> getAll() {
        return formRepository.findAll();
    }

    @Override
    public List<JSONForm> getAllAsJSON() {
        return formMapper.toJSONs(getAll());
    }

    @Override
    public void addSection(long formId, JSONSection jsonSection) {
        Form form = getById(formId);
        if (form == null) {
            log.severe("Form not found with ID: " + formId);
            return;
        }

        Section section = sectionMapper.fromJSON(jsonSection);
        section.setForm(form);
        form.getSections().add(section);
        save(form);

        log.info("Section added: " + jsonSection.getName());
    }

    @Override
    public Form getById(Long id) {
        return formRepository.getOne(id);
    }

    @Override
    public boolean existsById(Long id) {
        return formRepository.exists(id);
    }

    @Override
    public Form save(Form form) {
        return formRepository.save(form);
    }

    @Override
    public void deleteById(Long id) {
        formRepository.delete(id);
    }

    @Override
    public Form getByExcelSheetIndex(int excelSheetIndex) {
        return formRepository.findByExcelSheetIndex(excelSheetIndex);
    }
}
