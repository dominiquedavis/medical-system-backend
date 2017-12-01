package com.medicalsystem.repository

import com.medicalsystem.domain.Patient
import org.springframework.data.jpa.repository.JpaRepository

interface PatientRepository : JpaRepository<Patient, String>