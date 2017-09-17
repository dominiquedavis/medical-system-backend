package com.medicalsystem.repository;

import com.medicalsystem.model.value.IntegerFieldValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntegerFieldValueRepository extends JpaRepository<IntegerFieldValue, Integer> {
}
