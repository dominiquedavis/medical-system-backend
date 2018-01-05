package com.medicalsystem.converter.dtototemplate

import com.medicalsystem.converter.Converter
import com.medicalsystem.domain.dto.AddingFieldDTO
import com.medicalsystem.domain.template.Field
import com.medicalsystem.service.FieldService
import org.springframework.stereotype.Component

/**
 * Converts FieldDTO to a template Field.
 */
@Component
class AddingFieldDTOToTemplateConverter(private val optionConverter: PossibleValueDTOToOptionConverter) : Converter<AddingFieldDTO, Field> {

    override fun convert(source: AddingFieldDTO): Field =
        Field(name = source.name, type = source.type,
                possibleValues = optionConverter.convertAll(source.possibleValues).toMutableSet())
}