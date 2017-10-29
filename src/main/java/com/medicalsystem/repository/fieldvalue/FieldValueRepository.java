package com.medicalsystem.repository.fieldvalue;

import com.medicalsystem.model.Patient;
import com.medicalsystem.model.fieldvalue.FieldValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldValueRepository<T extends FieldValue<?>> extends JpaRepository<T, Long> {

    List<FieldValue<?>> findAllByPatient(Patient patient);

}
