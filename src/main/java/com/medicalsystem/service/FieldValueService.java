package com.medicalsystem.service;

import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.FieldValue;

import java.util.List;

public interface FieldValueService extends CRUDService<FieldValue, Integer> {

    /**
     * Returns all field values for a patient with the given id.
     * @param patientId - patient id
     * @return a list of field values
     */
    List<FieldValue> findAllByPatientId(int patientId);

    /**
     * Returns a value for a given field and patient.
     * @param field     - a Field object
     * @param patientId - patient id
     * @return FieldValue
     */
    FieldValue findByFieldAndPatientId(Field field, int patientId);

}
