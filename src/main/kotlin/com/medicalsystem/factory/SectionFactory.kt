package com.medicalsystem.factory

import com.medicalsystem.model.Section
import com.medicalsystem.properties.FormProperties.PropForm.PropSection

object SectionFactory : FromPropertiesFactory<PropSection, Section> {

    override fun createFromProperties(t: PropSection): Section {
        val section = Section(name = t.name)
        val fields = FieldFactory.createFromProperties(t.fields)

        section.fields = fields.toMutableList()
        fields.forEach { field -> field.section = section }

        return section
    }

    override fun createFromProperties(t: List<PropSection>): List<Section> =
            t.map(this::createFromProperties)
}