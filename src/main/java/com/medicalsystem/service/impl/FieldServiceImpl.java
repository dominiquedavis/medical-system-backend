package com.medicalsystem.service.impl;

import com.medicalsystem.model.Field;
import com.medicalsystem.model.Form;
import com.medicalsystem.repository.FieldRepository;
import com.medicalsystem.service.FieldService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;

    @Override
    public List<Field> getAll() {
        return fieldRepository.findAll();
    }

    @Override
    public List<Field> getAllByForm(Form form) {
        return fieldRepository.findAllByForm(form);
    }

    @Override
    public Map<Integer, Field> getExcelColumnIndexToFieldMap(Form form) {
        return getAllByForm(form).stream()
                .collect(Collectors.toMap(Field::getExcelColumnIndex, Function.identity()));
    }

    @Override
    public int getNextExcelColumnIndex(Form form) {
        return fieldRepository.findMaxExcelColumnIndexByForm(form) + 1;
    }

    @Override
    public Field getById(Long id) {
        return fieldRepository.findOne(id);
    }

    @Override
    public boolean existsById(Long id) {
        return fieldRepository.exists(id);
    }

    @Override
    public Field save(Field field) {
        return fieldRepository.save(field);
    }

    @Override
    public void deleteById(Long id) {
        fieldRepository.delete(id);
    }
}
