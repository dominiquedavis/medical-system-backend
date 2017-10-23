package com.medicalsystem.service.impl;

import com.medicalsystem.model.FormType;
import com.medicalsystem.model.Patient;
import com.medicalsystem.repository.PatientRepository;
import com.medicalsystem.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findById(Integer id) {
        return patientRepository.findOne(id);
    }

    @Override
    public Patient saveOrUpdate(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void delete(Patient patient) {
        patientRepository.delete(patient);
    }

    @Override
    public void deleteById(Integer id) {
        patientRepository.delete(id);
    }

    @Override
    public boolean exists(int patientId) {
        return patientRepository.exists(patientId);
    }

    @Override
    public List<Patient> findAllByFormType(FormType formType) {
        return patientRepository.findAllByFormType(formType);
    }

    @Override
    public boolean createPatient(int patientId) {
        if (exists(patientId)) {
            log.severe("Patient exists with ID: " + patientId);
            return false;
        }
        saveOrUpdate(new Patient(patientId));
        log.info("Patient created: " + patientId);
        return true;
    }
}
