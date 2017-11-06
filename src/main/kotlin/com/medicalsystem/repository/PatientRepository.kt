package com.medicalsystem.repository

import com.medicalsystem.model.Patient
import org.springframework.data.jpa.repository.JpaRepository

interface PatientRepository : JpaRepository<Patient, String>