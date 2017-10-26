package com.medicalsystem.repository.fieldvalue;

import com.medicalsystem.model.fieldvalue.FieldValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldValueRepository<T extends FieldValue<?>> extends JpaRepository<T, Long> {
}
