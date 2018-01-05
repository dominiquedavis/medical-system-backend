package com.medicalsystem.converter.dtototemplate

import com.medicalsystem.converter.Converter
import com.medicalsystem.domain.dto.FormDTO
import com.medicalsystem.domain.template.Form
import org.springframework.stereotype.Component

/**
 * Converts FormDTO to a template Form.
 */
@Component
class FormDTOToTemplateConverter(private val sectionConverter: SectionDTOToTemplateConverter) : Converter<FormDTO, Form> {

    override fun convert(source: FormDTO): Form =
            Form(name = source.name, sheetName = source.name,
                    sections = sectionConverter.convertAll(source.sections).toMutableSet())
}