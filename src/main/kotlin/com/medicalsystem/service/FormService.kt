package com.medicalsystem.service

import com.medicalsystem.converter.dtototemplate.FormDTOToTemplateConverter
import com.medicalsystem.converter.dtototemplate.SectionDTOToTemplateConverter
import com.medicalsystem.converter.templatetodto.FormToDTOConverter
import com.medicalsystem.domain.dto.FormDTO
import com.medicalsystem.domain.dto.SectionDTO
import com.medicalsystem.domain.template.Form
import com.medicalsystem.domain.template.Section
import com.medicalsystem.exception.NO_FORM_WITH_ID
import com.medicalsystem.repository.template.FormRepository
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class FormService(
        private val formRepository: FormRepository,
        private val templateToDtoConverter: FormToDTOConverter,
        private val dtoToEmptyFormConverter: FormDTOToTemplateConverter,
        private val dtoToEmptySectionConverter: SectionDTOToTemplateConverter,
        private val sectionService: SectionService
) : CRUDService<Form, Long>(formRepository) {

    /**
     * Returns a Form with a given sheet name.
     */
    fun findByName(formName: String): Form? =
            formRepository.findByName(formName)

    /**
     * Returns a Form with a given sheet name.
     */
    fun findBySheetName(sheetName: String): Form? =
            formRepository.findBySheetName(sheetName)

    /**
     * Returns the whole template as a list of FormDTO aggregate objects.
     */
    fun getTemplateForms(): List<FormDTO> {
        val allForms = findAll()
        val allDtoForms = templateToDtoConverter.convertAll(allForms)

        allDtoForms.forEach {
            it.sections = it.sections.sortedBy { it.id }
            it.sections.forEach {
                it.fields = it.fields.sortedBy { it.id }
            }
        }

        return allDtoForms
    }

    /**
     * Returns a list of all forms names.
     */
    fun getAllFormNames(): List<String> =
            formRepository.findAllFormNames()

    /**
     * Adds a new empty Form as template.
     */
    fun addTemplateForm(formDTO: FormDTO): FormDTO {
        // Convert FormDTO to empty Form
        val templateForm: Form = dtoToEmptyFormConverter.convert(formDTO)

        // Persist new Form
        save(templateForm)

        // Update generated ID in returned object
        formDTO.id = templateForm.id

        return formDTO
    }

    /**
     * Adds a new empty Section as template to the given Form.
     */
    fun addTemplateSection(formId: Long, sectionDTO: SectionDTO): SectionDTO {
        // Fetch Form object to which the Section will be bound
        val form: Form = findByID(formId) ?: throw EntityNotFoundException(NO_FORM_WITH_ID + formId)
        // Convert SectionDTO to Section
        val section: Section = dtoToEmptySectionConverter.convert(sectionDTO)

        // Bind Section to Form
        form.addSection(section)

        // Persist new Section
        sectionService.save(section)

        // Update generated ID in returned object
        sectionDTO.id = section.id

        return sectionDTO
    }

    fun updateForm(formId: Long, formDTO: FormDTO): FormDTO {
        // Fetch form object
        val form: Form = findByID(formDTO.id) ?: throw RuntimeException(NO_FORM_WITH_ID + formId)

        // Updates properties
        form.name = formDTO.name

        // Save updated form
        save(form)

        // Update ID in returned object
        formDTO.id = formId

        return formDTO
    }
}