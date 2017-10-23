package com.medicalsystem.repository;

import com.medicalsystem.model.Form;
import com.medicalsystem.model.FormType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Integer> {

    Form findByType(FormType type);

}
