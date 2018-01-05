package com.medicalsystem.converter.valuestodto

import com.medicalsystem.domain.Patient
import com.medicalsystem.domain.dto.FieldDTO
import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.value.FieldValue
import com.medicalsystem.exception.NO_FIELD_VALUE_FOR_FIELD_AND_PATIENT
import com.medicalsystem.service.FieldValueService
import org.springframework.stereotype.Component
import javax.persistence.EntityNotFoundException

@Component
class FilledFieldToDtoConverter(private val fieldValueService: FieldValueService) : ValuesConverter<Field, FieldDTO> {

    override fun convert(source: Field, patient: Patient): FieldDTO {
        val fieldDTO = FieldDTO(id = source.id, name = source.name, type = source.type)

        // Fetch FieldValue for a given Field and Patient
        val fieldValue: FieldValue<*> = fieldValueService.findByFieldAndPatient(source, patient)

        // Set values and possible values for a created DTO object
        fieldValue.setDtoValues(fieldDTO)
        fieldValue.setDtoPossibleValues(fieldDTO)

        return fieldDTO
    }
}