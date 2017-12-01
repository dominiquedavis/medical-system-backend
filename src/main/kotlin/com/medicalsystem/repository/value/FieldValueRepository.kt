package com.medicalsystem.repository.value

import com.medicalsystem.domain.value.FieldValue
import org.springframework.data.jpa.repository.JpaRepository

interface FieldValueRepository : JpaRepository<FieldValue<*>, Long>