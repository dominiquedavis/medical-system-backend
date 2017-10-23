package com.medicalsystem.service;

import com.medicalsystem.json.model.JSONField;
import com.medicalsystem.model.FormType;
import com.medicalsystem.model.field.Field;

import java.util.List;

public interface FieldService extends CRUDService<Field<?>, Integer> {

    Field<?> findByName(String name);

    Field<?> findByExcelColumn(int excelColumn, FormType formType);

    List<Field<?>> findAllByFormType(FormType formType);

    void addField(int formId, int sectionId, JSONField jsonField);

}

