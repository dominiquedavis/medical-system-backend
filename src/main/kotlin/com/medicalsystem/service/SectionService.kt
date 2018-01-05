package com.medicalsystem.service

import com.medicalsystem.converter.dtototemplate.AddingFieldDTOToTemplateConverter
import com.medicalsystem.converter.dtototemplate.SectionDTOToTemplateConverter
import com.medicalsystem.domain.dto.AddingFieldDTO
import com.medicalsystem.domain.dto.SectionDTO
import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.template.Section
import com.medicalsystem.exception.FIELD_EXISTS_WITH_NAME
import com.medicalsystem.exception.NO_SECTION_WITH_ID
import com.medicalsystem.repository.template.SectionRepository
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class SectionService(
        private val sectionRepository: SectionRepository,
        private val fieldConverter: AddingFieldDTOToTemplateConverter,
        private val fieldService: FieldService
) : CRUDService<Section, Long>(sectionRepository) {

    /**
     * Adds a new Field as template to the given Section.
     */
    fun addTemplateField(sectionId: Long, fieldDTO: AddingFieldDTO): AddingFieldDTO {
        // Fetch Section object to which the Field will be bound
        val section: Section = findByID(sectionId) ?: throw EntityNotFoundException(NO_SECTION_WITH_ID + sectionId)

        // Check if no formField with this name
        if (section.fields.any { it.name == fieldDTO.name }) {
            throw IllegalArgumentException(FIELD_EXISTS_WITH_NAME + fieldDTO.name)
        }

        // Convert FieldDTO to Field
        val field: Field = fieldConverter.convert(fieldDTO)

        // Set excel column
        field.columnIdx = fieldService.getNextColumnIndex()

        // Bind formField to section
        section.addField(field)

        // Persist newly created Field
        fieldService.save(field)

        // Update generated ID in returned object
        fieldDTO.id = field.id

        return fieldDTO
    }

    /**
     * Updates a Section.
     */
    fun updateSection(sectionId: Long, sectionDTO: SectionDTO): SectionDTO {
        // Fetch section object
        val section: Section = findByID(sectionId) ?: throw EntityNotFoundException(NO_SECTION_WITH_ID + sectionId)

        // Update properties
        section.name = sectionDTO.name

        // Persist updated object
        save(section)

        // Update ID of the returned object
        sectionDTO.id = sectionId

        return sectionDTO
    }
}