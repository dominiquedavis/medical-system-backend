package com.medicalsystem.repository;

import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.FieldValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldValueRepository<T extends FieldValue<?>> extends JpaRepository<T, Integer> {

    List<T> findAllByPatientId(int patientId);

    T findByFieldAndPatientId(Field<?> field, int patientId);

}
