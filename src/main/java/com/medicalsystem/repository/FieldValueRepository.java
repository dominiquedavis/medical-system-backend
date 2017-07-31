package com.medicalsystem.repository;

import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.FieldValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldValueRepository extends JpaRepository<FieldValue, Integer> {

    List<FieldValue> findAllByPatientId(int patientId);

    FieldValue findByFieldAndPatientId(Field field, int patientId);

}
