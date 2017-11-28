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
        formService.getAllAsDTO()

    @GetMapping("names")
    fun getFormNames(): List<String> =
        formService.getAllFormNames()

    @PostMapping
    fun addForm(@RequestBody formDTO: FormDTO): FormDTO =
        formService.addForm(formDTO)
}