package com.medicalsystem.service

import com.medicalsystem.model.Form
import com.medicalsystem.model.dto.FormDTO
import com.medicalsystem.model.dto.SectionDTO
import com.medicalsystem.repository.FormRepository

abstract class FormService(formRepository: FormRepository) : DefaultCRUDService<Form, Long, FormRepository>(formRepository) {
    abstract fun getBySheetIndex(sheetIndex: Int): Form?
    abstract fun getAllAsDTO(): List<FormDTO>
    abstract fun getFormDTOForPatient(patientId: String): FormDTO?
    abstract fun updateFormForPatient(patientId: String, formDTO: FormDTO): FormDTO
    abstract fun addSection(sectionDTO: SectionDTO, formId: Long)
    abstract fun getAllFormNames(): List<String>
    abstract fun addForm(formDTO: FormDTO): FormDTO
    abstract fun getNextSheetIndex(): Int
    abstract fun updateForm(formDTO: FormDTO): FormDTO
    abstract fun findByName(name: String): Form?
}