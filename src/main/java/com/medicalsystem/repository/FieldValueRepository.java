package com.medicalsystem.repository;

import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.FieldValue;

public interface FieldValueRepository extends GenericFieldValueRepository<FieldValue<? extends Field, ?>, Field> {
}
