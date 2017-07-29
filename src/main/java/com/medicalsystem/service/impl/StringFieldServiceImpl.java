package com.medicalsystem.service.impl;

import com.medicalsystem.model.field.StringField;
import com.medicalsystem.repository.StringFieldRepository;
import com.medicalsystem.service.StringFieldService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StringFieldServiceImpl implements StringFieldService {

    private final StringFieldRepository repository;

    @Override
    public List<StringField> findAll() {
        return repository.findAll();
    }

    @Override
    public StringField findById(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public StringField saveOrUpdate(StringField stringField) {
        return repository.save(stringField);
    }

    @Override
    public void delete(StringField stringField) {
        repository.delete(stringField);
    }

    @Override
    public void deleteById(Integer id) {
        repository.delete(id);
    }
}
