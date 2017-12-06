package com.medicalsystem.controller

import com.medicalsystem.model.Field
import com.medicalsystem.model.FieldType
import com.medicalsystem.model.Form
import com.medicalsystem.model.dto.AddingFieldDTO
import com.medicalsystem.model.dto.FormDTO
import com.medicalsystem.model.dto.SectionDTO
import com.medicalsystem.service.FieldService
import com.medicalsystem.service.FormService
import com.medicalsystem.service.SectionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/forms")
class FormController @Autowired constructor(val formService: FormService,
                                            val sectionService: SectionService,
                                            val fieldService: FieldService) {

    @GetMapping
    fun getForms(): List<FormDTO> =
            formService.getAllAsDTO()

    @GetMapping("names")
    fun getFormNames(): List<String> =
            formService.getAllFormNames()

    @PostMapping
    fun addForm(@RequestBody formDTO: FormDTO): FormDTO =
            formService.addForm(formDTO)

    @PutMapping
    fun updateForm(@RequestBody formDTO: FormDTO): FormDTO =
            formService.updateForm(formDTO)

    @PostMapping("{formId}/sections")
    fun addSection(@PathVariable formId: Long, @RequestBody sectionDTO: SectionDTO): SectionDTO {
        val form: Form = formService.getById(formId) ?: throw RuntimeException("Form not found with ID: $formId")
        return sectionService.addSection(form, sectionDTO)
    }

    @PutMapping("sections/{sectionId}")
    fun updateSection(@RequestBody sectionDTO: SectionDTO, @PathVariable sectionId: Long): SectionDTO {
        val section = sectionService.getById(sectionDTO.id) ?: throw RuntimeException("Section not found with ID: ${sectionDTO.id}")
        section.name = sectionDTO.name
        sectionService.save(section)
        return sectionDTO
    }

    @PostMapping("{formId}/sections/{sectionId}")
    fun addField(@PathVariable formId: Long, @PathVariable sectionId: Long, @RequestBody addingFieldDTO: AddingFieldDTO): AddingFieldDTO {
        val section = sectionService.getById(sectionId) ?: throw RuntimeException("Section not found with ID: $sectionId")
        val field = Field()
        field.id = addingFieldDTO.id
        field.name = addingFieldDTO.name
        field.type = addingFieldDTO.type ?: FieldType.TEXT
        field.section = section
        field.columnIndex = fieldService.getMaxColumnIndex() + 1

        val possVals = mutableMapOf<String, String>()
        addingFieldDTO.possibleValues?.forEach {
            possVals.put(it.excelKey, it.value)
        }
        field.possibleValues = possVals

        fieldService.save(field)
        return addingFieldDTO
    }

    @PutMapping("fields/{fieldId}")
    fun updateField(@PathVariable fieldId: Long, @RequestBody addingFieldDTO: AddingFieldDTO): AddingFieldDTO {
        val field = fieldService.getById(fieldId) ?: throw RuntimeException("Field not found with ID: $fieldId")
        field.name = addingFieldDTO.name
        field.type = addingFieldDTO.type ?: FieldType.TEXT
        val possVals = mutableMapOf<String, String>()
        addingFieldDTO.possibleValues?.forEach {
            possVals.put(it.excelKey, it.value)
        }
        field.possibleValues = possVals
        fieldService.save(field)
        return addingFieldDTO
    }
}