package com.medicalsystem.factory

import com.medicalsystem.model.Form
import com.medicalsystem.model.dto.FormDTO
import com.medicalsystem.properties.FormProperties.PropForm

object FormFactory : PropertiesFactory<PropForm, Form>, DTOFactory<FormDTO, Form> {

    override fun createFromProperties(t: List<PropForm>): List<Form> =
            t.map{ createFromProperties(it) }

    override fun createFromProperties(t: PropForm): Form {
        val form = Form(name = t.name, type = t.type, sheetIndex = t.index)
        val sections = SectionFactory.createFromProperties(t.sections)

        form.sections = sections.toMutableList()
        sections.forEach { section -> section.form = form }

        return form
    }

    override fun createEmptyDTO(us: List<Form>): List<FormDTO> =
            us.map { createEmptyDTO(it) }

    override fun createEmptyDTO(u: Form): FormDTO =
            FormDTO(
                    id = u.id,
                    type = u.type,
                    sections = SectionFactory.createEmptyDTO(u.sections)
            )

    override fun createEmptyFromDTO(ts: List<FormDTO>): List<Form> {
        TODO("not implemented")
    }

    override fun createEmptyFromDTO(t: FormDTO): Form {
        TODO("not implemented")
    }
}