package com.medicalsystem.service

import com.medicalsystem.model.Field
import com.medicalsystem.model.Form
import com.medicalsystem.repository.FieldRepository

abstract class FieldService(fieldRepository: FieldRepository) : DefaultCRUDService<Field, Long, FieldRepository>(fieldRepository) {
    abstract fun getAllByForm(form: Form): List<Field>
    abstract fun getColumnIndexToFieldMap(form: Form): Map<Int, Field>
    abstract fun getMaxColumnIndex(): Int
}