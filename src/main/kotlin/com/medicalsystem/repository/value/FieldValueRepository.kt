package com.medicalsystem.repository.value

import com.medicalsystem.model.value.FieldValue
import org.springframework.data.jpa.repository.JpaRepository

interface FieldValueRepository<T : FieldValue<*>> : JpaRepository<T, Long>