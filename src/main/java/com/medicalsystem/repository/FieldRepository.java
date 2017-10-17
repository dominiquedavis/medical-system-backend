package com.medicalsystem.repository;

import com.medicalsystem.model.field.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field<?>, Integer> {

    Field<?> findByName(String name);

    Field<?> findByOpenExcelColumn(int openExcelColumn);

    Field<?> findByEvarExcelColumn(int evarExcelColumn);

}
