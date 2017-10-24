package com.medicalsystem.service.impl;

import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.FieldValue;
import com.medicalsystem.repository.*;
import com.medicalsystem.service.FieldValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class FieldValueServiceImpl implements FieldValueService {

    private final FieldValueRepository fieldValueRepository;

    private final List<GenericFieldValueRepository<? extends FieldValue<? extends Field, ?>, ? extends Field>> repositories;

    @Autowired
    public FieldValueServiceImpl(FieldValueRepository fieldValueRepository,
                                 DateFieldValueRepository dateFieldValueRepository,
                                 NumberFieldValueRepository numberFieldValueRepository,
                                 TextFieldValueRepository textFieldValueRepository,
                                 SelectFieldValueRepository selectFieldValueRepository,
                                 MultipleSelectFieldValueRepository multipleSelectFieldValueRepository) {
        this.fieldValueRepository = fieldValueRepository;
        this.repositories = Arrays.asList(
                dateFieldValueRepository,
                numberFieldValueRepository,
                textFieldValueRepository,
                selectFieldValueRepository,
                multipleSelectFieldValueRepository
        );
    }

    @Override
    public List<FieldValue<? extends Field, ?>> findAll() {
        return repositories.stream()
                .map(JpaRepository::findAll)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public FieldValue<? extends Field, ?> findById(Long id) {
        return repositories.stream()
                .map(repository -> repository.findOne(id))
                .filter(Objects::nonNull)
                .findAny()
                .orElse(null);
    }

    @Override
    public FieldValue<? extends Field, ?> saveOrUpdate(FieldValue<? extends Field, ?> fieldValue) {
        return fieldValueRepository.save(fieldValue);
    }

    @Override
    public void delete(FieldValue<? extends Field, ?> fieldValue) {
        deleteById(fieldValue.getId());
    }

    @Override
    public void deleteById(Long id) {
        repositories.forEach(repository -> {
            if (repository.exists(id))
                repository.delete(id);
        });
    }

    @Override
    public List<FieldValue<? extends Field, ?>> findAllByPatientId(int patientId) {
        return repositories.stream()
                .map(repository -> repository.findAllByPatientId(patientId))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public FieldValue<? extends Field, ?> findByPatientIdAndField(int patientId, Field field) {
        /*return repositories.stream()
                .map(repository -> repository.findByPatientIdAndField(patientId, field))
                .filter(Objects::nonNull)
                .findAny()
                .orElse(null);*/
        return null;
    }

}
