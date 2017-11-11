package com.medicalsystem.service.impl

import com.medicalsystem.factory.FormFactory
import com.medicalsystem.factory.SectionFactory
import com.medicalsystem.model.Form
import com.medicalsystem.model.Section
import com.medicalsystem.model.dto.FormDTO
import com.medicalsystem.model.dto.SectionDTO
import com.medicalsystem.repository.FormRepository
import com.medicalsystem.service.FormService
import com.medicalsystem.util.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
@Transactional
class FormServiceImpl @Autowired constructor(val formRepository: FormRepository) : FormService(formRepository) {

    override fun getBySheetIndex(sheetIndex: Int): Form? =
            formRepository.findBySheetIndex(sheetIndex)

    override fun getAllAsDTO(): List<FormDTO> =
            FormFactory.createEmptyDTO(getAll())

    override fun addSection(sectionDTO: SectionDTO, formId: Long) {
        val form: Form = getById(formId) ?: throw EntityNotFoundException("No form with ID: {$formId}")
        val section: Section = SectionFactory.createEmptyFromDTO(sectionDTO)
        form.sections.add(section)
        section.form = form
        save(form)
        logger().info("Section added successfully: $sectionDTO")
    }
}