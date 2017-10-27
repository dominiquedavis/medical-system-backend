package com.medicalsystem.service;

import com.medicalsystem.model.Field;
import com.medicalsystem.model.Form;

import java.util.List;
import java.util.Map;

public interface FieldService extends CRUDService<Field, Long> {

    List<Field> getAllByForm(Form form);

    Map<Integer, Field> getExcelColumnIndexToFieldMap(Form form);

}
