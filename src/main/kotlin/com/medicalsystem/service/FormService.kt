package com.medicalsystem.service

import com.medicalsystem.domain.template.Form
import com.medicalsystem.repository.template.FormRepository
import org.springframework.stereotype.Service

@Service
class FormService(private val formRepository: FormRepository) : CRUDService<Form, Long>(formRepository) {

    fun getBySheetIndex(index: Int): Form? =
            formRepository.findBySheetIndex(index)
}