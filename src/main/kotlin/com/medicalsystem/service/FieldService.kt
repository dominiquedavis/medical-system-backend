package com.medicalsystem.service

import com.medicalsystem.converter.dtototemplate.AddingFieldDTOToTemplateConverter
import com.medicalsystem.domain.dto.AddingFieldDTO
import com.medicalsystem.domain.template.Field
import com.medicalsystem.exception.NO_FIELD_WITH_ID
import com.medicalsystem.repository.template.FieldRepository
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class FieldService(
        private val fieldRepository: FieldRepository,
        private val fieldConverter: AddingFieldDTOToTemplateConverter
) : CRUDService<Field, Long>(fieldRepository) {

    /**
     * Returns the next available index of the column in the excel file.
     */
    fun getNextColumnIndex(): Int =
            fieldRepository.findMaxColIndex() + 1

    /**
     * Updates a Field with a given ID.
     */
    fun updateField(fieldId: Long, fieldDTO: AddingFieldDTO): AddingFieldDTO {
        // Fetch current Field
        val currentField: Field = findByID(fieldId) ?: throw EntityNotFoundException(NO_FIELD_WITH_ID + fieldId)

        // Convert given formField DTO
        val newField: Field = fieldConverter.convert(fieldDTO)

        // Update properties
        currentField.name = newField.name
        currentField.type = newField.type
        currentField.possibleValues = newField.possibleValues

        // Persist updated object
        save(newField)

        // Update ID of the returned object
        fieldDTO.id = fieldId

        return fieldDTO
    }
}