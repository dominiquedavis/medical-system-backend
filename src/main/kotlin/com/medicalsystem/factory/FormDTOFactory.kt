package com.medicalsystem.factory

import com.medicalsystem.model.Form
import com.medicalsystem.model.dto.FormDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FormDTOFactory @Autowired constructor(val sectionDTOFactory: SectionDTOFactory) : DTOFactory<FormDTO, Form> {

    override fun toDTO(u: Form, patientId: String?): FormDTO =
            FormDTO(
                    id = u.id,
                    type = u.type,
                    sections = sectionDTOFactory.toDTO(u.sections, patientId)
            )
}