package com.medicalsystem.converter.templatetodto

import com.medicalsystem.converter.Converter
import com.medicalsystem.domain.dto.FieldDTO
import com.medicalsystem.domain.template.Field
import org.springframework.stereotype.Component

/**
 * Converts template Field to FieldDTO.
 * Values are always empty, possible values are filled for (multiple) select fields.
 */
@Component
class FieldToDTOConverter(private val possibleValuesConverter: PossibleValuesToDTOConverter) : Converter<Field, FieldDTO> {

    override fun convert(source: Field): FieldDTO {
        val fieldDTO = FieldDTO()
        fieldDTO.id = source.id
        fieldDTO.name = source.name
        fieldDTO.type = source.type
        fieldDTO.possibleValues = possibleValuesConverter.convertAll(source.possibleValues).sortedBy { it.excelKey }
        return fieldDTO
    }
}