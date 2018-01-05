package com.medicalsystem.converter.valuestodto

import com.medicalsystem.domain.Patient
import com.medicalsystem.domain.dto.FormDTO
import com.medicalsystem.domain.dto.SectionDTO
import com.medicalsystem.domain.template.Form
import com.medicalsystem.domain.template.Section
import org.springframework.stereotype.Component

@Component
class FilledFormToDtoConverter(private val sectionConverter: ValuesConverter<Section, SectionDTO>) : ValuesConverter<Form, FormDTO> {

    override fun convert(source: Form, patient: Patient): FormDTO =
            FormDTO(id = source.id, name = source.name,
                    sections = sectionConverter.convertAll(source.sections, patient))
}