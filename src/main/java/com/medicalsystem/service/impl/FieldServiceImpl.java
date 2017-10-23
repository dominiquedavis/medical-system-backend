package com.medicalsystem.service.impl;

import com.medicalsystem.json.mapper.FieldMapper;
import com.medicalsystem.json.model.JSONField;
import com.medicalsystem.model.Form;
import com.medicalsystem.model.FormType;
import com.medicalsystem.model.Section;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.repository.FieldRepository;
import com.medicalsystem.service.FieldService;
import com.medicalsystem.service.FormService;
import com.medicalsystem.service.SectionService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
@Log
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;

    private final FormService formService;
    private final SectionService sectionService;

    private final FieldMapper fieldMapper;

    @Override
    public List<Field<?>> findAll() {
        return fieldRepository.findAll();
    }

    @Override
    public Field<?> findById(Integer id) {
        return fieldRepository.findOne(id);
    }

    @Override
    public Field<?> saveOrUpdate(Field<?> field) {
        return fieldRepository.save(field);
    }

    @Override
    public void delete(Field<?> field) {
        fieldRepository.delete(field);
    }

    @Override
    public void deleteById(Integer id) {
        fieldRepository.delete(id);
    }

    @Override
    public Field findByName(String name) {
        return fieldRepository.findByName(name);
    }

    @Override
    public Field findByExcelColumn(int excelColumn, FormType formType) {
        return formType == FormType.OPEN ?
                fieldRepository.findByOpenExcelColumn(excelColumn) :
                fieldRepository.findByEvarExcelColumn(excelColumn);
    }

    @Override
    public List<Field<?>> findAllByFormType(FormType formType) {
        return formType == FormType.OPEN ?
                fieldRepository.findAllByOpenExcelColumnIsNot(-1) :
                fieldRepository.findAllByEvarExcelColumnIsNot(-1);
    }

    @Override
    public void addField(int formId, int sectionId, JSONField jsonField) {
        Form form = formService.findById(formId);
        if (form == null) {
            log.severe("Form not found with ID: " + formId);
            return;
        }

        Section section = sectionService.findById(sectionId);
        if (section == null) {
            log.severe("Section not found with ID: " + sectionId);
            return;
        }

        Field<?> field = fieldMapper.fromJSON(jsonField);

        // Persist field
        saveOrUpdate(field);

        // Add field to section
        section.addField(field);

        // Update section
        sectionService.saveOrUpdate(section);

        log.info("Field added: " + field.getName());
    }

}
