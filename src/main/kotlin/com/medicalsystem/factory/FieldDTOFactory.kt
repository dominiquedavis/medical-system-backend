package com.medicalsystem.factory

import com.medicalsystem.model.Field
import com.medicalsystem.model.dto.FieldDTO
import org.springframework.stereotype.Component

@Component
class FieldDTOFactory : DTOFactory<FieldDTO, Field> {

    override fun toDTO(u: Field, patientId: String?): FieldDTO =
            FieldDTO(
                    id = u.id,
                    name = u.name,
                    type = u.type,
                    values = getValues(patientId),
                    possibleValues = getPossibleValues(patientId)
            )

    private fun getValues(patientId: String?): List<*>? {
        patientId?.let {
            // TODO
        }
        return null
    }

    private fun getPossibleValues(patientId: String?): List<*>? {
        patientId?.let {
            // TODO
        }
        return null
    }
}