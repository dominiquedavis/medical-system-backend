package com.medicalsystem.converter.dtototemplate

import com.medicalsystem.converter.Converter
import com.medicalsystem.domain.dto.SectionDTO
import com.medicalsystem.domain.template.Section
import org.springframework.stereotype.Component

/**
 * Converts SectionDTO to a template Section.
 */
@Component
class SectionDTOToTemplateConverter : Converter<SectionDTO, Section> {

    override fun convert(source: SectionDTO): Section =
            Section(name = source.name)
}