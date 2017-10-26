package com.medicalsystem.service.impl;

import com.medicalsystem.model.Form;
import com.medicalsystem.repository.FormRepository;
import com.medicalsystem.service.FormService;
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

    @Override
    public List<Form> getAll() {
        return formRepository.findAll();
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
}
