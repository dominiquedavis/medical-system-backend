package com.medicalsystem.service;

import com.medicalsystem.model.field.Field;

import java.util.List;

public interface FieldService extends CRUDService<Field<?>, Integer> {

    Field<?> findByName(String name);

    Field<?> findByExcelColumn(int excelColumn);

    List<Field<?>> findAllOpenFields();
}

