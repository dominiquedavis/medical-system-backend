package com.medicalsystem.service

import com.medicalsystem.converter.valuestodto.ValuesConverter
import com.medicalsystem.domain.Patient
import com.medicalsystem.domain.dto.FieldDTO
import com.medicalsystem.domain.dto.FormDTO
import com.medicalsystem.domain.template.Form
import com.medicalsystem.domain.value.FieldValue
import com.medicalsystem.exception.*
import com.medicalsystem.repository.PatientRepository
import com.medicalsystem.util.logger
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class PatientService(
        private val patientRepository: PatientRepository,
        private val formService: FormService,
        private val fieldService: FieldService,
        private val fieldValueService: FieldValueService,
        private val valuesFormConverter: ValuesConverter<Form, FormDTO>
) : CRUDService<Patient, String>(patientRepository) {

    /**
     * Returns all Patients bound to a given form.
     */
    fun findAllByForm(form: Form): List<Patient> =
            patientRepository.findAllByForm(form)

    /**
     * Creates a new Patient, binding him to a Form with w given name.
     * Returns true on success.
     */
    fun create(patientId: String, formName: String): Boolean {
        // Check if patient exists with an ID
        if (exists(patientId)) {
            logger().error(PATIENT_EXISTS_WITH_ID + patientId)
            return false
        }

        // Get form with name or throw an exception
        val form: Form = formService.findByName(formName) ?: throw EntityNotFoundException(NO_FORM_WITH_NAME + formName)

        // Create and persist new Patient
        val newPatient = save(Patient(patientId, form))

        // Create empty values for all fields for this patient
        form.sections.flatMap { it.fields }.forEach {
            val emptyFieldValue = FieldValue.createInstanceByFieldType(it.type)
            emptyFieldValue.field = it
            emptyFieldValue.patient = newPatient
            fieldValueService.save(emptyFieldValue)
        }

        return true
    }

    /**
     * Returns a FormDTO aggregate with formField values filled for a Patient with a given ID.
     */
    fun getFilledFormForPatient(patientId: String): FormDTO {
        // Get Patient
        val patient: Patient = findByID(patientId) ?: throw EntityNotFoundException(NO_PATIENT_WITH_ID + patientId)

        // Get Form to which the patient is bound
        val form: Form = patient.form ?: throw RuntimeException(FORM_IS_NULL)

        // Convert Form to DTO and sort lists by elements' IDs
        val filledForm = valuesFormConverter.convert(form, patient)
        filledForm.sections = filledForm.sections.sortedBy { it.id }
        filledForm.sections.forEach {
            it.fields = it.fields.sortedBy { it.id }
        }

        return filledForm
    }

    /**
     * Updates formField values for a given Patient.
     */
    fun updateFormForPatient(patientId: String, formDTO: FormDTO): FormDTO {
        // Get Patient
        val patient: Patient = findByID(patientId) ?: throw EntityNotFoundException(NO_PATIENT_WITH_ID + patientId)

        // Get FieldDTO objects for Fields which values should be changed
        val fieldDTOs: List<FieldDTO> = formDTO.sections.flatMap { it.fields }.sortedBy { it.id }

        // Get all FieldValues for a given Patient
        val fieldValues: List<FieldValue<*>> = fieldValueService.findAllForPatient(patient).sortedBy { it.field?.id }

        if (!fieldDTOs.correspondsTo(fieldValues)) {
            throw IllegalArgumentException(FIELDS_DIFFER)
        }

        // Iterate over fields and update values
        for ((fieldDTO, fieldValue) in fieldDTOs zip fieldValues) {

            if (fieldDTO.values.isEmpty()) {
                continue
            }

            // Update FieldValue
            try {
                fieldValue.updateValue(fieldDTO.values)
            } catch (e: Exception) {
                logger().error(ERROR_UPDATING_VALUE + fieldDTO.values + " " + e.message)
                continue
            }

            // Persist updated FieldValue
            fieldValueService.save(fieldValue)
        }

        return formDTO
    }

    /**
     * Checks if fieldDTOs contains inforamtion about the same fields as fieldValues.
     */
    private fun List<FieldDTO>.correspondsTo(fieldValues: List<FieldValue<*>>): Boolean {
        if (this.size != fieldValues.size) {
            return false
        }

        // Check if id pairs are the same
        for ((id1, id2) in this.map { it.id } zip fieldValues.map { it.field?.id }) {
            if (id1 != id2) {
                return false
            }
        }

        return true
    }
}