package com.medicalsystem.properties

import com.medicalsystem.model.Field
import com.medicalsystem.model.Form
import com.medicalsystem.model.Section
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import com.medicalsystem.properties.FormProperties.PropForm
import com.medicalsystem.properties.FormProperties.PropForm.PropSection
import com.medicalsystem.properties.FormProperties.PropForm.PropSection.PropField

@Component
class FromPropertiesModelFactory @Autowired constructor(val formProperties: FormProperties) {

    fun createForms(): List<Form> =
            formProperties.forms.map { createForm(it) }

    private fun createForm(propForm: PropForm): Form {
        val form = Form(name = propForm.name, type = propForm.type, sheetIndex = propForm.index)
        val sections = propForm.sections.map { createSection(it) }.toMutableList()

        form.sections = sections
        sections.forEach { it.form = form }

        return form
    }

    private fun createSection(propSection: PropSection): Section {
        val section = Section(name = propSection.name)
        val fields = propSection.fields.map { createField(it) }.toMutableList()

        section.fields = fields
        fields.forEach { it.section = section }

        return section
    }

    private fun createField(propField: PropField): Field =
        Field(
                name = propField.name,
                type = propField.type,
                columnIndex = propField.excelColumnIndex,
                possibleValues = propField.options.associateBy({ it.key }, { it.`val` })
        )
}