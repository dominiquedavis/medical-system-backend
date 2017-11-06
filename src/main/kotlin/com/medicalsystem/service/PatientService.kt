package com.medicalsystem.service

import com.medicalsystem.model.Form
import com.medicalsystem.model.Patient
import com.medicalsystem.repository.PatientRepository

abstract class PatientService(patientRepository: PatientRepository) : DefaultCRUDService<Patient, String, PatientRepository>(patientRepository) {
    abstract fun create(id: String, form: Form): Patient?
}