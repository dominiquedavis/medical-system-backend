package com.medicalsystem.service.impl

import com.medicalsystem.factory.FieldValueFactory
import com.medicalsystem.model.Form
import com.medicalsystem.model.Patient
import com.medicalsystem.model.value.FieldValue
import com.medicalsystem.repository.FormRepository
import com.medicalsystem.repository.PatientRepository
import com.medicalsystem.service.FieldService
import com.medicalsystem.service.FieldValueService
import com.medicalsystem.service.FormService
import com.medicalsystem.service.PatientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PatientServiceImpl @Autowired constructor(
        val patientRepository: PatientRepository,
        val formRepository: FormRepository,
        val fieldService: FieldService,
        val fieldValueService: FieldValueService) : PatientService(patientRepository) {

    override fun create(id: String, form: Form): Patient? =
            if (exists(id)) {
                null
            } else {
                save(Patient(id, form))
            }

    override fun create(patientId: String, formName: String): Boolean {
        val form: Form? = formRepository.findByName(formName)
        return if (exists(patientId) || form == null) {
            false
        } else {
            val patient = Patient(patientId, form)
            patientRepository.saveAndFlush(patient)
            fieldService.getAllByForm(form).forEach {
                val fieldValue: FieldValue<*> = FieldValueFactory.fromFieldAndStringValue(it, "")
                fieldValue.patient = patient
                fieldValueService.save(fieldValue)
            }
            true
        }
    }

    override fun getAllByForm(form: Form): List<Patient> =
            patientRepository.findAllByForm(form)
}