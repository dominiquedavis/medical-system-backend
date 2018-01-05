package com.medicalsystem.repository

import com.medicalsystem.domain.Patient
import com.medicalsystem.domain.template.Form
import org.springframework.data.jpa.repository.JpaRepository

interface PatientRepository : JpaRepository<Patient, String> {

    fun findAllByForm(form: Form): List<Patient>
}