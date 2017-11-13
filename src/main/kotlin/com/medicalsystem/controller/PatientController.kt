package com.medicalsystem.controller

import com.medicalsystem.model.dto.FormDTO
import com.medicalsystem.service.FormService
import com.medicalsystem.service.PatientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException

@RestController
@RequestMapping("api/patients")
class PatientController @Autowired constructor(
        val patientService: PatientService,
        val formService: FormService) {

    @GetMapping("{patientId}")
    fun exists(@PathVariable patientId: String): Boolean =
            patientService.exists(patientId)

    @PostMapping("{patientId}")
    fun createPatient(@PathVariable patientId: String): Boolean =
            TODO("need info about form")

    @GetMapping("{patientId}/patientForm")
    fun getForm(@PathVariable patientId: String): FormDTO =
            formService.getFormDTOForPatient(patientId) ?: throw EntityNotFoundException("No form for patient with ID: $patientId")
}