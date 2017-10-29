package com.medicalsystem.service.impl;

import com.medicalsystem.model.Form;
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
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> getAllByForm(Form form) {
        return patientRepository.findAllByForm(form);
    }

    @Override
    public Patient getById(Long id) {
        return patientRepository.findOne(id);
    }

    @Override
    public boolean existsById(Long id) {
        return patientRepository.exists(id);
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deleteById(Long id) {
        patientRepository.delete(id);
    }

    @Override
    public boolean create(long patientId) {
        if (existsById(patientId)) {
            log.severe("Patient exists with ID: " + patientId);
            return false;
        }
        Patient patient = new Patient();
        patient.setId(patientId);
        save(patient);
        log.info("Patient created: " + patientId);
        return true;
    }
}
