package com.medicalsystem.service

import com.medicalsystem.model.Field
import com.medicalsystem.model.Patient
import com.medicalsystem.model.value.FieldValue

interface FieldValueService : CRUDService<FieldValue<*>, Long> {
    fun getByFieldAndPatient(field: Field, patient: Patient): FieldValue<*>?
    fun getAllFieldValuesForPatient(patient: Patient): List<FieldValue<*>>
}