package com.medicalsystem.service.impl;

import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.FieldValue;
import com.medicalsystem.repository.*;
import com.medicalsystem.service.FieldValueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class FieldValueServiceImpl implements FieldValueService {

    private final FieldValueRepository<FieldValue> fieldValueRepository;
    private final List<FieldValueRepository<? extends FieldValue>> fieldValueRepositories;

    @Autowired
    public FieldValueServiceImpl(FieldValueRepository<FieldValue> fieldValueRepository,
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
    public List<FieldValue> findAll() {
        /*return Stream.of(
                dateFieldValueRepository.findAll(),
                doubleFieldValueRepository.findAll(),
                integerFieldValueRepository.findAll(),
                textFieldValueRepository.findAll())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());*/

        List<FieldValue> fieldValues = new ArrayList<>();


        System.out.println("XXXXXXXXXXXXXXXXX: " + fieldValueRepositories.size());

        for (FieldValueRepository<? extends FieldValue> r : fieldValueRepositories) {

            List<? extends FieldValue> fv = r.findAll();
            fieldValues.addAll(fv);
        }

        return fieldValues;
    }

    @Override
    public List<FieldValue> findAllByPatientId(int patientId) {
        //return fieldValueRepository.findAllByPatientId(patientId);
        return null;
    }

    @Override
    public FieldValue findByFieldAndPatientId(Field field, int patientId) {
        //return fieldValueRepository.findByFieldAndPatientId(field, patientId);
        return null;
    }

    @Override
    public FieldValue findById(Integer id) {
        //return fieldValueRepository.findOne(id);
        return null;
    }

    @Override
    public FieldValue saveOrUpdate(FieldValue fieldValue) {
        return fieldValueRepository.save(fieldValue);
    }

    @Override
    public void delete(FieldValue fieldValue) {
        //fieldValueRepository.delete(fieldValue);
    }

    @Override
    public void deleteById(Integer id) {
        //fieldValueRepository.delete(id);
    }
}
