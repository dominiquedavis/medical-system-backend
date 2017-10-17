package com.medicalsystem.service.impl;

import com.medicalsystem.model.FormType;
import com.medicalsystem.model.field.Field;
import com.medicalsystem.repository.FieldRepository;
import com.medicalsystem.service.FieldService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;

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
    public List<Field<?>> findAllOpenFields() {
        return new ArrayList<>();
    }
}
