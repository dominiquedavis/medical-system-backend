package com.medicalsystem.repository;

import com.medicalsystem.model.value.TextFieldValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextFieldValueRepository extends JpaRepository<TextFieldValue, Integer> {
}
