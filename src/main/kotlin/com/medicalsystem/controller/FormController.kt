package com.medicalsystem.controller

import com.medicalsystem.domain.dto.AddingFieldDTO
import com.medicalsystem.domain.dto.FieldDTO
import com.medicalsystem.domain.dto.FormDTO
import com.medicalsystem.domain.dto.SectionDTO
import com.medicalsystem.domain.template.Form
import com.medicalsystem.service.FieldService
import com.medicalsystem.service.FormService
import com.medicalsystem.service.SectionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/forms")
class FormController(
        private val formService: FormService,
        private val sectionService: SectionService,
        private val fieldService: FieldService
) {

    /**
     * Returns the whole template as a list of FormDTO aggregate objects.
     */
    @GetMapping
    fun getForms(): List<FormDTO> =
            formService.getTemplateForms()

    /**
     * Returns a list of all forms names.
     */
    @GetMapping("names")
    fun getFormNames(): List<String> =
            formService.getAllFormNames()

    /**
     * Adds a new empty Form as template.
     */
    @PostMapping
    fun addForm(@RequestBody formDTO: FormDTO): FormDTO =
            formService.addTemplateForm(formDTO)

    /**
     * Updates a Form.
     */
    @PostMapping("{formId}")
    fun updateForm(@RequestBody formDTO: FormDTO, @PathVariable formId: Long): FormDTO =
            formService.updateForm(formId, formDTO)

    /**
     * Adds a new empty Section as template to the given Form.
     */
    @PostMapping("{formId}/sections")
    fun addSection(@PathVariable formId: Long, @RequestBody sectionDTO: SectionDTO): SectionDTO =
            formService.addTemplateSection(formId, sectionDTO)

    /**
     * Updates a Section.
     */
    @PostMapping("sections/{sectionId}")
    fun updateSection(@RequestBody sectionDTO: SectionDTO, @PathVariable sectionId: Long): SectionDTO =
            sectionService.updateSection(sectionId, sectionDTO)

    /**
     * Adds a new Field as template to the given Section.
     */
    @PostMapping("{formId}/sections/{sectionId}")
    fun addField(@PathVariable formId: Long, @PathVariable sectionId: Long, @RequestBody fieldDTO: AddingFieldDTO): AddingFieldDTO =
            sectionService.addTemplateField(sectionId, fieldDTO)

    /**
     * Updates a Field.
     */
    @PostMapping("fields/{fieldId}")
    fun updateField(@PathVariable fieldId: Long, @RequestBody addingFieldDTO: AddingFieldDTO): AddingFieldDTO =
            fieldService.updateField(fieldId, addingFieldDTO)
}