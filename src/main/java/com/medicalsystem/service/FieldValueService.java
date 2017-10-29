package com.medicalsystem.service;

import com.medicalsystem.model.Patient;
import com.medicalsystem.model.fieldvalue.FieldValue;

import java.util.List;

public interface FieldValueService extends CRUDService<FieldValue<?>, Long> {

    List<FieldValue<?>> getAllByPatient(Patient patient);

}
