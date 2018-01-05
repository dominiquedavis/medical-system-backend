package com.medicalsystem.converter.templatetodto

import com.medicalsystem.converter.Converter
import com.medicalsystem.domain.dto.FormDTO
import com.medicalsystem.domain.template.Form
import org.springframework.stereotype.Component

/**
 * Converts template Form to FormDTO.
 */
@Component
class FormToDTOConverter(private val sectionConverter: SectionToDTOConverter) : Converter<Form, FormDTO> {

    override fun convert(source: Form): FormDTO =
            FormDTO(id = source.id, name = source.name,
                    sections = sectionConverter.convertAll(source.sections))
}