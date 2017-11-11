package com.medicalsystem.service

import com.medicalsystem.model.Form
import com.medicalsystem.model.dto.FormDTO
import com.medicalsystem.repository.FormRepository

abstract class FormService(formRepository: FormRepository) : DefaultCRUDService<Form, Long, FormRepository>(formRepository) {
    abstract fun getBySheetIndex(sheetIndex: Int): Form?
    abstract fun getAllAsDTO(): List<FormDTO>
}