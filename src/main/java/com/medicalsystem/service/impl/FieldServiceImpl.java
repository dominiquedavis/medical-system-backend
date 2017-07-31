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
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Field<?>> findAll() {
        return fieldRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
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
}
