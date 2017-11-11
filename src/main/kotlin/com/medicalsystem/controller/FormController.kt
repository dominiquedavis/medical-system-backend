package com.medicalsystem.controller

import com.medicalsystem.model.dto.FormDTO
import com.medicalsystem.service.FormService
import com.medicalsystem.service.SectionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/forms")
class FormController @Autowired constructor(val formService: FormService, val sectionService: SectionService) {

    @GetMapping
    fun getForms(): List<FormDTO> = formService.getAllAsDTO()
}