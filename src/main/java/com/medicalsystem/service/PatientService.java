package com.medicalsystem.service;

import com.medicalsystem.model.Form;
import com.medicalsystem.model.Patient;

import java.util.List;

public interface PatientService extends CRUDService<Patient, Long> {
    List<Patient> getAllByForm(Form form);
    boolean create(long patientId);
}
