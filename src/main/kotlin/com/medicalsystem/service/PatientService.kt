package com.medicalsystem.service

import com.medicalsystem.domain.Patient
import com.medicalsystem.repository.PatientRepository
import org.springframework.stereotype.Service

@Service
class PatientService(private val patientRepository: PatientRepository) : CRUDService<Patient, String>(patientRepository)