package com.medicalsystem.controller

import com.medicalsystem.model.dto.FormDTO
import com.medicalsystem.model.dto.SectionDTO
import com.medicalsystem.service.FormService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/forms")
class FormController @Autowired constructor(val formService: FormService) {

    @GetMapping
    fun getForms(): List<FormDTO> =
            formService.getAllAsDTO(null)

    @PostMapping("{formId}/sections")
    fun addSection(@PathVariable formId: Long, @RequestBody sectionDTO: SectionDTO) =
            formService.addSection(sectionDTO, formId)
}