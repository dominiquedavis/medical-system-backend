package com.medicalsystem.service.impl;

import com.medicalsystem.model.field.Field;
import com.medicalsystem.repository.FieldRepository;
import com.medicalsystem.service.FieldService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;

    @Override
    public List<Field> findAll() {
        return fieldRepository.findAll();
    }

    @Override
    public Field findById(Long id) {
        return fieldRepository.findOne(id);
    }

    @Override
    public Field saveOrUpdate(Field field) {
        return fieldRepository.save(field);
    }

    @Override
    public void delete(Field field) {
        fieldRepository.delete(field);
    }

    @Override
    public void deleteById(Long id) {
        fieldRepository.delete(id);
    }
}
