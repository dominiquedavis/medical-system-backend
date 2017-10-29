package com.medicalsystem.repository;

import com.medicalsystem.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long> {
    Form findByExcelSheetIndex(int excelSheetIndex);

}
