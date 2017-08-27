package com.medicalsystem.service.impl;

import com.medicalsystem.model.Form;
import com.medicalsystem.repository.FormRepository;
import com.medicalsystem.service.FormService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class FormServiceImpl implements FormService {

    private final FormRepository formRepository;

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
    public Form findByPatientId(int patientId) {
        return null;
    }
}
