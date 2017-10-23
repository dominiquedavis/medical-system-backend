package com.medicalsystem.service.impl;

import com.medicalsystem.json.mapper.FormMapper;
import com.medicalsystem.json.model.JSONField;
import com.medicalsystem.json.model.JSONForm;
import com.medicalsystem.json.model.JSONSection;
import com.medicalsystem.model.Form;
import com.medicalsystem.model.FormType;
import com.medicalsystem.model.Patient;
import com.medicalsystem.repository.FormRepository;
import com.medicalsystem.service.FormService;
import com.medicalsystem.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
@Log
public class FormServiceImpl implements FormService {

    private final FormRepository formRepository;
    private final FormMapper formMapper;
    private final PatientService patientService;

    @Override
    public List<Form> findAll() {
        return formRepository.findAll();
    }

    @Override
    public Form findById(Integer id) {
        return formRepository.findOne(id);
    }

    @Override
    public Form saveOrUpdate(Form form) {
        return formRepository.save(form);
    }

    @Override
    public void delete(Form form) {
        formRepository.delete(form);
    }

    @Override
    public void deleteById(Integer id) {
        formRepository.delete(id);
    }

    @Override
    public Form findByType(FormType formType) {
        return formRepository.findByType(formType);
    }

    @Override
    public JSONForm getForm(int patientId) {
        Patient patient = patientService.findById(patientId);
        if (patient == null) {
            log.severe("Patient not found with ID: " + patientId);
            return new JSONForm();
        }

        Form form = findByType(patient.getFormType());
        if (form == null) {
            log.severe("Form not found with type: " + patient.getFormType());
            return new JSONForm();
        }

        return formMapper.toJSON(form, patient);
    }

    @Override
    public JSONForm updateForm(int patientId, JSONForm jsonForm) {
        return null;
    }

    @Override
    public List<JSONForm> getForms() {
        return findAll().stream().map(formMapper::toJSON).collect(Collectors.toList());
    }

    @Override
    public void addSection(int formId, JSONSection jsonSection) {

    }

    @Override
    public void addField(int formId, int sectionId, JSONField jsonField) {

    }

}
