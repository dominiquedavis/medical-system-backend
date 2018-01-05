package com.medicalsystem.converter.templatetodto

import com.medicalsystem.converter.Converter
import com.medicalsystem.domain.dto.SectionDTO
import com.medicalsystem.domain.template.Section
import org.springframework.stereotype.Component

/**
 * Converts template Section to SectionDTO.
 */
@Component
class SectionToDTOConverter(private val fieldConverter: FieldToDTOConverter) : Converter<Section, SectionDTO> {

    override fun convert(source: Section): SectionDTO =
            SectionDTO(id = source.id, name = source.name,
                    fields = fieldConverter.convertAll(source.fields))
}