package com.medicalsystem.repository.value

import com.medicalsystem.domain.Patient
import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.value.FieldValue
import org.springframework.data.jpa.repository.JpaRepository

interface FieldValueRepository : JpaRepository<FieldValue<*>, Long> {

    fun findAllByPatient(patient: Patient): List<FieldValue<*>>

    fun findByFieldAndPatient(field: Field, patient: Patient): FieldValue<*>?
}