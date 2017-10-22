package com.medicalsystem.repository;

import com.medicalsystem.model.FormType;
import com.medicalsystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    List<Patient> findAllByFormType(FormType formType);

}
