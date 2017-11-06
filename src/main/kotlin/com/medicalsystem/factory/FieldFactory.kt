package com.medicalsystem.factory

import com.medicalsystem.model.Field
import com.medicalsystem.properties.FormProperties.PropForm.PropSection.PropField

object FieldFactory : FromPropertiesFactory<PropField, Field> {

    override fun createFromProperties(t: PropField): Field =
            Field(
                    name = t.name,
                    type = t.type,
                    columnIndex = t.excelColumnIndex,
                    possibleValues = t.options.associateBy({ it.key }, { it.`val` })
            )

    override fun createFromProperties(t: List<PropField>): List<Field> =
            t.map(this::createFromProperties)
}