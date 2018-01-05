package com.medicalsystem.converter.valuestodto

import com.medicalsystem.domain.Patient
import com.medicalsystem.domain.dto.FieldDTO
import com.medicalsystem.domain.dto.SectionDTO
import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.template.Section
import org.springframework.stereotype.Component

@Component
class FilledSectionToDtoConverter(private val fieldConverter: ValuesConverter<Field, FieldDTO>) : ValuesConverter<Section, SectionDTO> {

    override fun convert(source: Section, patient: Patient): SectionDTO =
            SectionDTO(id = source.id, name = source.name,
                    fields = fieldConverter.convertAll(source.fields, patient))
}