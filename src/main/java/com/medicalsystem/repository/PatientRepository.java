package com.medicalsystem.repository;

import com.medicalsystem.model.Form;
import com.medicalsystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllByForm(Form form);
    boolean existsByPatientId(String patientId);
}
