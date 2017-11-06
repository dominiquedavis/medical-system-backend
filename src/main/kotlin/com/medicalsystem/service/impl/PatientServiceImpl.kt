package com.medicalsystem.service.impl

import com.medicalsystem.model.Form
import com.medicalsystem.model.Patient
import com.medicalsystem.repository.PatientRepository
import com.medicalsystem.service.PatientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PatientServiceImpl @Autowired constructor(patientRepository: PatientRepository) : PatientService(patientRepository) {
    override fun create(id: String, form: Form): Patient? = if (exists(id)) { null } else { save(Patient(id, form)) }
}