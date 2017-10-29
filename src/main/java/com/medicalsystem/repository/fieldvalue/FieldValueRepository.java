package com.medicalsystem.repository.fieldvalue;

import com.medicalsystem.model.Field;
import com.medicalsystem.model.Patient;
import com.medicalsystem.model.fieldvalue.FieldValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldValueRepository<T extends FieldValue<?>> extends JpaRepository<T, Long> {
    List<T> findAllByPatient(Patient patient);
    T findByFieldAndPatient(Field field, Patient patient);
}
