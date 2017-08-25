package com.medicalsystem.service;

import com.medicalsystem.model.field.Field;

public interface FieldService extends CRUDService<Field, Integer> {

    Field findByName(String name);

}
