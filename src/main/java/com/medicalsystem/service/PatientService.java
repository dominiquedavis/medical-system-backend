package com.medicalsystem.service;

import com.medicalsystem.model.FormType;
import com.medicalsystem.model.Patient;

import java.util.List;

public interface PatientService extends CRUDService<Patient, Integer> {

    boolean exists(int patientId);

    List<Patient> findAllByFormType(FormType formType);

    boolean createPatient(int patientId);

}
