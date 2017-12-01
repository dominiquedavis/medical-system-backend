package com.medicalsystem.service

import com.medicalsystem.domain.value.FieldValue
import com.medicalsystem.repository.value.FieldValueRepository
import org.springframework.stereotype.Service

@Service
class FieldValueService(
        private val fieldValueRepository: FieldValueRepository
) : CRUDService<FieldValue<*>, Long>(fieldValueRepository)