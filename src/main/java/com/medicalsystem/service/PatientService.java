package com.medicalsystem.service;

import com.medicalsystem.model.FormType;
import com.medicalsystem.model.Patient;

import java.util.List;

public interface PatientService extends CRUDService<Patient, Integer> {

    List<Patient> findAllByFormType(FormType formType);

}
