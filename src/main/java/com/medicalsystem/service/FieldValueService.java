package com.medicalsystem.service;

import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.FieldValue;

import java.util.List;

public interface FieldValueService extends CRUDService<FieldValue<? extends Field, ?>, Long> {

    List<FieldValue<? extends Field, ?>> findAllByPatientId(int patientId);

    FieldValue<? extends Field, ?> findByPatientIdAndField(int patientId, Field field);

}
