package com.medicalsystem.factory

import com.medicalsystem.model.Patient
import com.medicalsystem.model.Section
import com.medicalsystem.model.dto.SectionDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SectionDTOFactory @Autowired constructor(val fieldDTOFactory: FieldDTOFactory) : DTOFactory<SectionDTO, Section> {

    override fun toDTO(u: Section, patient: Patient?): SectionDTO =
            SectionDTO(
                    id = u.id,
                    name = u.name,
                    fields = fieldDTOFactory.toDTO(u.fields.asSequence().distinct().toList(), patient)
            )

    override fun emptyFromDTO(t: SectionDTO): Section {
        TODO("not implemented")
    }
}