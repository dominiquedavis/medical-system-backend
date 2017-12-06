package com.medicalsystem.service.impl

import com.medicalsystem.factory.SectionDTOFactory
import com.medicalsystem.model.Form
import com.medicalsystem.model.dto.SectionDTO
import com.medicalsystem.repository.SectionRepository
import com.medicalsystem.service.SectionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SectionServiceImpl @Autowired constructor(sectionRepository: SectionRepository,
                                                val sectionDTOFactory: SectionDTOFactory) : SectionService(sectionRepository) {

    override fun addSection(form: Form, sectionDTO: SectionDTO): SectionDTO {
        val section = sectionDTOFactory.fromDTO(sectionDTO)
        section.form = form
        save(section)
        return sectionDTO
    }
}