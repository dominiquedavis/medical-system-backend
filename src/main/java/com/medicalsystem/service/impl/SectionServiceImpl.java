package com.medicalsystem.service.impl;

import com.medicalsystem.mapper.FieldMapper;
import com.medicalsystem.model.Field;
import com.medicalsystem.model.Form;
import com.medicalsystem.model.Section;
import com.medicalsystem.model.json.JSONField;
import com.medicalsystem.repository.SectionRepository;
import com.medicalsystem.service.FieldService;
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
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final FieldMapper fieldMapper;
    private final FieldService fieldService;

    @Override
    public List<Section> getAll() {
        return sectionRepository.findAll();
    }

    @Override
    public Section getById(Long id) {
        return sectionRepository.findOne(id);
    }

    @Override
    public boolean existsById(Long id) {
        return sectionRepository.exists(id);
    }

    @Override
    public Section save(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public void deleteById(Long id) {
        sectionRepository.delete(id);
    }

    @Override
    public void addField(long sectionId, JSONField jsonField) {
        Section section = getById(sectionId);
        if (section == null) {
            log.severe("Section not found with ID: " + sectionId);
            return;
        }

        Field field = fieldMapper.fromJSON(jsonField);
        field.setSection(section);
        setExcelColumnIndex(field);
        section.getFields().add(field);
        save(section);

        log.info("Field added: " + field.getName());
    }

    private void setExcelColumnIndex(Field field) {
        Form form = field.getSection().getForm();
        int excelColumnIndex = fieldService.getNextExcelColumnIndex(form);
        field.setExcelColumnIndex(excelColumnIndex);
    }
}
