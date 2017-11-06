package com.medicalsystem.factory

import com.medicalsystem.model.Form
import com.medicalsystem.properties.FormProperties.PropForm

object FormFactory : FromPropertiesFactory<PropForm, Form> {

    override fun createFromProperties(t: PropForm): Form {
        val form = Form(name = t.name, type = t.type, sheetIndex = t.index)
        val sections = SectionFactory.createFromProperties(t.sections)

        form.sections = sections.toMutableList()
        sections.forEach { section -> section.form = form }

        return form
    }

    override fun createFromProperties(t: List<PropForm>): List<Form> =
            t.map(this::createFromProperties)
}