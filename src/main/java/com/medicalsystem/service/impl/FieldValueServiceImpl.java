package com.medicalsystem.service.impl;

import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.FieldValue;
import com.medicalsystem.repository.*;
import com.medicalsystem.service.FieldValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class FieldValueServiceImpl implements FieldValueService {

    private final FieldValueRepository<FieldValue<?>> fieldValueRepository;
    private final List<FieldValueRepository<? extends FieldValue>> fieldValueRepositories;

    @Autowired
    public FieldValueServiceImpl(FieldValueRepository<FieldValue<?>> fieldValueRepository,
                                 DateFieldValueRepository dateFieldValueRepository,
                                 DoubleFieldValueRepository doubleFieldValueRepository,
                                 IntegerFieldValueRepository integerFieldValueRepository,
                                 TextFieldValueRepository textFieldValueRepository) {
        this.fieldValueRepository = fieldValueRepository;
        this.fieldValueRepositories = Arrays.asList(
                dateFieldValueRepository,
                doubleFieldValueRepository,
                integerFieldValueRepository,
                textFieldValueRepository
        );
    }

    @Override
    public List<FieldValue<?>> findAll() {
        List<FieldValue<?>> fieldValues = new LinkedList<>();

        for (FieldValueRepository<? extends FieldValue> repository : fieldValueRepositories) {
            List<? extends FieldValue<?>> fv = repository.findAll();
            fieldValues.addAll(fv);
        }

        return fieldValues;
    }

    @Override
    public List<FieldValue<?>> findAllByPatientId(int patientId) {
        List<FieldValue<?>> fieldValues = new LinkedList<>();

        for (FieldValueRepository<? extends FieldValue<?>> repository : fieldValueRepositories) {
            List<? extends FieldValue<?>> fv = repository.findAllByPatientId(patientId);
            fieldValues.addAll(fv);
        }

        return fieldValues;
    }

    @Override
    public FieldValue<?> findByFieldAndPatientId(Field<?> field, int patientId) {
        FieldValue fieldValue = null;

        for (FieldValueRepository<? extends FieldValue<?>> repository : fieldValueRepositories)
            if ((fieldValue = repository.findByFieldAndPatientId(field, patientId)) != null)
                break;

        return fieldValue;
    }

    @Override
    public FieldValue<?> findById(Integer id) {
        FieldValue fieldValue = null;

        for (FieldValueRepository<? extends FieldValue<?>> repository : fieldValueRepositories)
            if ((fieldValue = repository.findOne(id)) != null)
                break;

        return fieldValue;
    }

    @Override
    public FieldValue<?> saveOrUpdate(FieldValue<?> fieldValue) {
        return fieldValueRepository.save(fieldValue);
    }

    @Override
    public void delete(FieldValue<?> fieldValue) {
        this.deleteById(fieldValue.getId());
    }

    @Override
    public void deleteById(Integer id) {
        for (FieldValueRepository<? extends FieldValue<?>> repository : fieldValueRepositories) {
            if (repository.exists(id)) {
                repository.delete(id);
            }
        }
    }
}
