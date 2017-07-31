package com.medicalsystem.repository;

import com.medicalsystem.model.field.Field;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;

@Lazy
public interface FieldRepository extends JpaRepository<Field<?>, Integer> {
}
