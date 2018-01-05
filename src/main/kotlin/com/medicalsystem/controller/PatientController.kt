package com.medicalsystem.controller

import com.medicalsystem.domain.dto.FormDTO
import com.medicalsystem.service.PatientService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/patients")
class PatientController(private val patientService: PatientService) {

    /**
     * Returns true if patient with a given ID exists.
     */
    @GetMapping("{patientId}")
    fun exists(@PathVariable patientId: String): Boolean =
            patientService.exists(patientId)

    /**
     * Creates a new Patient, binding him to a Form with w given name.
     * Returns true on success.
     */
    @PostMapping("{patientId}")
    fun createPatient(@PathVariable patientId: String, @RequestBody formName: String): Boolean =
            patientService.create(patientId, formName)

    /**
     * Returns a FormDTO aggregate with formField values filled for a Patient with a given ID.
     */
    @GetMapping("{patientId}/patientForm")
    fun getForm(@PathVariable patientId: String): FormDTO =
            patientService.getFilledFormForPatient(patientId)

    /**
     * Updates formField values for a given Patient.
     */
    @PutMapping("{patientId}/patientForm")
    fun updateForm(@PathVariable patientId: String, @RequestBody formDTO: FormDTO): FormDTO =
            patientService.updateFormForPatient(patientId, formDTO)
}