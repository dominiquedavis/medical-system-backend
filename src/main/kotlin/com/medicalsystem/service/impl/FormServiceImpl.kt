package com.medicalsystem.service.impl

import com.medicalsystem.model.Form
import com.medicalsystem.repository.FormRepository
import com.medicalsystem.service.FormService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FormServiceImpl @Autowired constructor(val formRepository: FormRepository) : FormService(formRepository) {
    override fun getBySheetIndex(sheetIndex: Int): Form? = formRepository.findBySheetIndex(sheetIndex)
}