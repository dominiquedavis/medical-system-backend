package com.medicalsystem.service

import com.medicalsystem.domain.Patient
import com.medicalsystem.domain.template.Form
import com.medicalsystem.repository.PatientRepository
import org.springframework.stereotype.Service

@Service
class PatientService(private val patientRepository: PatientRepository) : CRUDService<Patient, String>(patientRepository) {

    fun create(id: String, form: Form): Patient? =
            if (exists(id))
                null
            else {
                save(Patient(id, form))
            }
}