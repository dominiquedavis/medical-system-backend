package com.medicalsystem.factory

import com.medicalsystem.model.Section
import com.medicalsystem.model.dto.SectionDTO
import com.medicalsystem.properties.FormProperties.PropForm.PropSection

object SectionFactory : PropertiesFactory<PropSection, Section>, DTOFactory<SectionDTO, Section> {

    override fun createFromProperties(t: List<PropSection>): List<Section> =
            t.map(this::createFromProperties)

    override fun createFromProperties(t: PropSection): Section {
        val section = Section(name = t.name)
        val fields = FieldFactory.createFromProperties(t.fields)

        section.fields = fields.toMutableList()
        fields.forEach { field -> field.section = section }

        return section
    }

    override fun createEmptyDTO(us: List<Section>): List<SectionDTO> =
            us.map { createEmptyDTO(it) }

    override fun createEmptyDTO(u: Section): SectionDTO =
            SectionDTO(
                    id = u.id,
                    name = u.name,
                    fields = FieldFactory.createEmptyDTO(u.fields)
            )

    override fun createEmptyFromDTO(ts: List<SectionDTO>): List<Section> =
            ts.map { createEmptyFromDTO(it) }

    override fun createEmptyFromDTO(t: SectionDTO): Section =
            Section(
                    id = t.id,
                    name = t.name
            )
}