package com.medicalsystem.factory

import com.medicalsystem.model.Field
import com.medicalsystem.model.FieldType.*
import com.medicalsystem.model.Patient
import com.medicalsystem.model.dto.FieldDTO
import com.medicalsystem.model.value.DateFieldValue
import com.medicalsystem.model.value.FieldValue
import com.medicalsystem.model.value.MultipleSelectFieldValue
import com.medicalsystem.model.value.SelectFieldValue
import com.medicalsystem.service.FieldValueService
import com.medicalsystem.util.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.EntityNotFoundException

@Component
class FieldDTOFactory @Autowired constructor(
        val fieldValueService: FieldValueService) : DTOFactory<FieldDTO, Field> {

    override fun toDTO(u: Field, patient: Patient?): FieldDTO {
        val fieldDTO = FieldDTO(id = u.id, name = u.name, type = u.type)

        patient?.let {
            val fieldValue: FieldValue<*> = fieldValueService.getByFieldAndPatient(u, patient)
                    ?: throw EntityNotFoundException("No field value for $u and patient '$patient'")

            when (u.type) {
                TEXT, NUMBER -> {
                    fieldDTO.values = listOf(fieldValue.value)
                    fieldDTO.possibleValues = emptyList<Any>()
                }
                DATE -> {
                    val dateValue = (fieldValue as DateFieldValue).value
                    fieldDTO.values = listOf(DateUtils.toString(dateValue))
                    fieldDTO.possibleValues = emptyList<Any>()
                }
                SELECT -> {
                    val value: String? = (fieldValue as SelectFieldValue).value
                    fieldDTO.values = listOf(u.possibleValues[value])
                    fieldDTO.possibleValues = u.possibleValues.values.toList()
                }
                MULTIPLE_SELECT -> {
                    val values: List<String> = (fieldValue as MultipleSelectFieldValue).value
                    fieldDTO.values = values.map { u.possibleValues[it] }
                    fieldDTO.possibleValues = u.possibleValues.values.toList()
                }
            }
        }

        return fieldDTO
    }

    override fun emptyFromDTO(t: FieldDTO): Field {
        TODO("not implemented")
    }
}