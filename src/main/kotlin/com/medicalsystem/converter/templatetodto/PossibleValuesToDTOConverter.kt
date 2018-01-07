package com.medicalsystem.converter.templatetodto

import com.medicalsystem.converter.Converter
import com.medicalsystem.domain.dto.PossibleValueDTO
import com.medicalsystem.domain.template.Option
import org.springframework.stereotype.Component

/**
 * Converts Option to PossibleValueDTO.
 */
@Component
class PossibleValuesToDTOConverter : Converter<Option, PossibleValueDTO> {

    override fun convert(source: Option): PossibleValueDTO =
            PossibleValueDTO(id = source.id, excelKey = source.key, value = source.value)
}