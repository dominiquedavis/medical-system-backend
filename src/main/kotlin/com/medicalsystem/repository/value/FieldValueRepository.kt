package com.medicalsystem.repository.value

import com.medicalsystem.model.Field
import com.medicalsystem.model.Patient
import com.medicalsystem.model.value.FieldValue
import org.springframework.data.jpa.repository.JpaRepository

interface FieldValueRepository<T : FieldValue<*>> : JpaRepository<T, Long> {
    fun findByFieldAndPatient(field: Field, patient: Patient): T?
}