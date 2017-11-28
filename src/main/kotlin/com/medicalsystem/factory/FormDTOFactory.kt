package com.medicalsystem.factory

import com.medicalsystem.model.Form
import com.medicalsystem.model.Patient
import com.medicalsystem.model.Section
import com.medicalsystem.model.dto.FormDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FormDTOFactory @Autowired constructor(val sectionDTOFactory: SectionDTOFactory) : DTOFactory<FormDTO, Form> {

    override fun toDTO(u: Form, patient: Patient?): FormDTO =
            FormDTO(
                    id = u.id,
                    type = u.type,
                    sections = sectionDTOFactory.toDTO(u.sections.asSequence().distinct().toList(), patient)
            )

    override fun fromDTO(t: FormDTO): Form =
        Form(
            id = t.id,
            name = t.type,
            type = t.type,
            sections = sectionDTOFactory.fromDTO(t.sections).toMutableList()
        )
}