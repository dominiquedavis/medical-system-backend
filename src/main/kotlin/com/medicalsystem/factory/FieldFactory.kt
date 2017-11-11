package com.medicalsystem.factory

import com.medicalsystem.model.Field
import com.medicalsystem.model.dto.FieldDTO
import com.medicalsystem.properties.FormProperties.PropForm.PropSection.PropField

object FieldFactory : PropertiesFactory<PropField, Field>, DTOFactory<FieldDTO, Field> {

    override fun createFromProperties(t: List<PropField>): List<Field>
            = t.map(this::createFromProperties)

    override fun createFromProperties(t: PropField): Field =
            Field(
                    name = t.name,
                    type = t.type,
                    columnIndex = t.excelColumnIndex,
                    possibleValues = t.options.associateBy({ it.key }, { it.`val` })
            )

    override fun createEmptyDTO(us: List<Field>): List<FieldDTO> =
            us.map { createEmptyDTO(it) }

    override fun createEmptyDTO(u: Field): FieldDTO =
            FieldDTO(
                    id = u.id,
                    name = u.name,
                    type = u.type,
                    values = null,
                    possibleValues = null
            )
}