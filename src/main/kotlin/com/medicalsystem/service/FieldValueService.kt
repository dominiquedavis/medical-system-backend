package com.medicalsystem.service

import com.medicalsystem.domain.Patient
import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.value.FieldValue
import com.medicalsystem.repository.value.FieldValueRepository
import org.springframework.stereotype.Service

@Service
class FieldValueService(private val fieldValueRepository: FieldValueRepository) : CRUDService<FieldValue<*>, Long>(fieldValueRepository) {

    fun findAllForPatient(patient: Patient): List<FieldValue<*>> =
            fieldValueRepository.findAllByPatient(patient)

    fun findByFieldAndPatient(field: Field, patient: Patient): FieldValue<*> {
        fieldValueRepository.findByFieldAndPatient(field, patient)?.let {
            return it
        }

        // Value not found, create new empty one
        val emptyFieldValue = FieldValue.createInstanceByFieldType(field.type)
        emptyFieldValue.field = field
        emptyFieldValue.patient = patient

        return save(emptyFieldValue)
    }

    /**
     * Same as above but not creating empty value if not found
     */
    fun findByFieldAndPatientNoCreate(field: Field, patient: Patient): FieldValue<*>? =
            fieldValueRepository.findByFieldAndPatient(field, patient)

    /**
     * Same as above but not creating empty value if not found and by name
     */
    fun findByFieldAndPatientByNameNoCreate(fieldName: String, patient: Patient): FieldValue<*>? =
            fieldValueRepository.findByFieldNameAndPatient(fieldName, patient)
}