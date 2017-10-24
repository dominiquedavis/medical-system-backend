package com.medicalsystem.repository;

import com.medicalsystem.model.field.Field;
import com.medicalsystem.model.value.FieldValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenericFieldValueRepository<T extends FieldValue<? extends Field, ?>, U extends Field> extends JpaRepository<T, Long> {

    List<T> findAllByPatientId(int patientId);

    @Query("")
    T findByPatientIdAndField(int patientId, U field);

}
