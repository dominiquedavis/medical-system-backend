package com.medicalsystem.service

import com.medicalsystem.domain.template.Field
import com.medicalsystem.repository.template.FieldRepository
import org.springframework.stereotype.Service

@Service
class FieldService(private val fieldRepository: FieldRepository) : CRUDService<Field, Long>(fieldRepository)