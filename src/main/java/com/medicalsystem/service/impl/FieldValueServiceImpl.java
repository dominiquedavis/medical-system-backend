package com.medicalsystem.service.impl;

import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.FieldValue;
import com.medicalsystem.repository.*;
import com.medicalsystem.service.FieldValueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class FieldValueServiceImpl implements FieldValueService {

    private final FieldValueRepository fieldValueRepository;
    private final DateFieldValueRepository dateFieldValueRepository;
    private final DoubleFieldValueRepository doubleFieldValueRepository;
    private final IntegerFieldValueRepository integerFieldValueRepository;
    private final TextFieldValueRepository textFieldValueRepository;

    @Override
    public List<FieldValue> findAll() {
        return Stream.of(
                dateFieldValueRepository.findAll(),
                doubleFieldValueRepository.findAll(),
                integerFieldValueRepository.findAll(),
                textFieldValueRepository.findAll())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<FieldValue> findAllByPatientId(int patientId) {
        return fieldValueRepository.findAllByPatientId(patientId);
    }

    @Override
    public FieldValue findByFieldAndPatientId(Field field, int patientId) {
        return fieldValueRepository.findByFieldAndPatientId(field, patientId);
    }

    @Override
    public FieldValue findById(Integer id) {
        return fieldValueRepository.findOne(id);
    }

    @Override
    public FieldValue saveOrUpdate(FieldValue fieldValue) {
        return fieldValueRepository.save(fieldValue);
    }

    @Override
    public void delete(FieldValue fieldValue) {
        fieldValueRepository.delete(fieldValue);
    }

    @Override
    public void deleteById(Integer id) {
        fieldValueRepository.delete(id);
    }
}
