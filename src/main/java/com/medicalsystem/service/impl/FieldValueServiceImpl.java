package com.medicalsystem.service.impl;

import com.medicalsystem.model.fieldvalue.FieldValue;
import com.medicalsystem.repository.fieldvalue.FieldValueRepository;
import com.medicalsystem.service.FieldValueService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@Log
public class FieldValueServiceImpl implements FieldValueService {

    private final FieldValueRepository<FieldValue<?>> superClassRepository;
    private final List<FieldValueRepository<? extends FieldValue<?>>> subClassRepositories;

    @Autowired
    public FieldValueServiceImpl(FieldValueRepository<FieldValue<?>> superClassRepository,
                                 List<FieldValueRepository<? extends FieldValue<?>>> subClassRepositories) {
        subClassRepositories.remove(superClassRepository);
        this.superClassRepository = superClassRepository;
        this.subClassRepositories = subClassRepositories;
    }

    @Override
    public List<FieldValue<?>> getAll() {
        return subClassRepositories.stream()
                .map(JpaRepository::findAll)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public FieldValue<?> getById(Long id) {
        return subClassRepositories.stream()
                .map(repository -> repository.findOne(id))
                .filter(Objects::nonNull)
                .findAny()
                .orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return subClassRepositories.stream()
                .filter(repository -> repository.exists(id))
                .count() > 0;
    }

    @Override
    public FieldValue<?> save(FieldValue<?> fieldValue) {
        return superClassRepository.save(fieldValue);
    }

    @Override
    public void deleteById(Long id) {
        subClassRepositories.forEach(repository -> {
            if (repository.exists(id)) {
                repository.delete(id);
            }
        });
    }
}
