package com.medicalsystem.service.impl

import com.medicalsystem.model.Field
import com.medicalsystem.model.Form
import com.medicalsystem.repository.FieldRepository
import com.medicalsystem.service.FieldService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FieldServiceImpl @Autowired constructor(val fieldRepository: FieldRepository) : FieldService(fieldRepository) {
    override fun getAllByForm(form: Form): List<Field> = fieldRepository.findAllByForm(form)
    override fun getColumnIndexToFieldMap(form: Form): Map<Int, Field> = getAllByForm(form).associateBy { it.columnIndex }
    override fun getMaxColumnIndex(): Int = fieldRepository.findMaxColIndex()
}