package com.medicalsystem.service.impl

import com.medicalsystem.factory.FormDTOFactory
import com.medicalsystem.model.FieldType.*
import com.medicalsystem.model.Form
import com.medicalsystem.model.Patient
import com.medicalsystem.model.dto.FieldDTO
import com.medicalsystem.model.dto.FormDTO
import com.medicalsystem.model.dto.SectionDTO
import com.medicalsystem.model.value.*
import com.medicalsystem.repository.FormRepository
import com.medicalsystem.service.FieldValueService
import com.medicalsystem.service.FormService
import com.medicalsystem.service.PatientService
import com.medicalsystem.util.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.text.ParseException
import java.util.*
import javax.persistence.EntityNotFoundException

@Service
@Transactional
class FormServiceImpl @Autowired constructor(
        val formRepository: FormRepository,
        val formDTOFactory: FormDTOFactory,
        val patientService: PatientService,
        val fieldValueService: FieldValueService) : FormService(formRepository) {

    override fun getBySheetIndex(sheetIndex: Int): Form? =
            formRepository.findBySheetIndex(sheetIndex)

    override fun getAllAsDTO(): List<FormDTO> =
            formDTOFactory.toDTO(getAll(), null)

    override fun getAllFormNames(): List<String> =
        formRepository.findAllFormNames()

    override fun getFormDTOForPatient(patientId: String): FormDTO? {
        val patient: Patient = patientService.getById(patientId) ?:
            throw JpaObjectRetrievalFailureException(EntityNotFoundException("No patient with ID: $patientId"))
        patient.form?.let {
            return formDTOFactory.toDTO(it, patient)
        }
        return null
    }

    override fun updateFormForPatient(patientId: String, formDTO: FormDTO): FormDTO {
        val patient: Patient = patientService.getById(patientId) ?: throw EntityNotFoundException("No patient with ID: $patientId")
        patient.form?.let {
            val fieldsDTO: List<FieldDTO> = formDTO.sections
                    .asSequence()
                    .map { it.fields }
                    .filterNotNull()
                    .flatMap { it.asSequence() }
                    .toList()

            val fieldValues: List<FieldValue<*>> = fieldValueService.getAllFieldValuesForPatient(patient)
                    .asSequence()
                    .sortedWith(compareBy { it.field?.id })
                    .toList()

            if (fieldsDTO.size != fieldValues.size) {
                throw RuntimeException("fieldsDTO != fieldValues")
            }

            fieldValues.forEachIndexed { index, fieldValue ->
                val values: List<*> = fieldsDTO[index].values ?: throw RuntimeException("Values are null")

                if (values[0] == null)
                    return@forEachIndexed

                when (fieldValue.field?.type) {
                    TEXT -> {
                        val value: String = values[0] as String
                        val textFieldValue = fieldValue as TextFieldValue
                        textFieldValue.value = value
                        fieldValueService.save(textFieldValue)
                    }
                    NUMBER -> {
                        val value: Double =
                                if (values[0] is Int)
                                    (values[0] as Int).toDouble()
                                else
                                    values[0] as Double
                        val numberFieldValue = fieldValue as NumberFieldValue
                        numberFieldValue.value = value
                        fieldValueService.save(numberFieldValue)
                    }
                    DATE -> {
                        val value: Date? = try {
                            DateUtils.fromString(values[0] as String)
                        } catch (e: ParseException) {
                            null
                        }
                        val dateFieldValue = fieldValue as DateFieldValue
                        dateFieldValue.value = value
                        fieldValueService.save(dateFieldValue)
                    }
                    SELECT -> {
                        val selectFieldValue = fieldValue as SelectFieldValue
                        if (values[0] == null) {
                            selectFieldValue.value = null
                        } else {
                            val value: String = values[0] as String
                            selectFieldValue.setValueByMapValue(value)
                        }
                        fieldValueService.save(selectFieldValue)
                    }
                    MULTIPLE_SELECT -> {
                        val vals: List<String> = values.map { it as String }
                        val multipleSelectFieldValue = fieldValue as MultipleSelectFieldValue
                        multipleSelectFieldValue.setValuesByMapValues(vals)
                        fieldValueService.save(multipleSelectFieldValue)
                    }
                    null -> throw RuntimeException("Field is null")
                }
            }
        }
        return formDTO
    }

    override fun addForm(formDTO: FormDTO): FormDTO {
        val form: Form = formDTOFactory.emptyFromDTO(formDTO)
        // Set sheet index to the next available
        form.sheetIndex = getNextSheetIndex()
        save(form)
        return formDTO
    }

    override fun getNextSheetIndex(): Int =
        formRepository.findMaxSheetIndex() + 1

    override fun addSection(sectionDTO: SectionDTO, formId: Long) {
        TODO()
    }
}