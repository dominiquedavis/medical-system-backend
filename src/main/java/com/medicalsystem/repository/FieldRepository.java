package com.medicalsystem.repository;

import com.medicalsystem.model.Field;
import com.medicalsystem.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Long> {

    @Query("SELECT f FROM Field f JOIN f.section s JOIN s.form fo WHERE fo = :form")
    List<Field> findAllByForm(@Param("form") Form form);

    @Query("SELECT MAX(f.excelColumnIndex) FROM Field f JOIN f.section s JOIN s.form fo WHERE fo = :form")
    int findMaxExcelColumnIndexByForm(@Param("form") Form form);
}
