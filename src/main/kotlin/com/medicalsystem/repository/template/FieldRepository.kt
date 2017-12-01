package com.medicalsystem.repository.template

import com.medicalsystem.domain.template.Field
import org.springframework.data.jpa.repository.JpaRepository

interface FieldRepository : JpaRepository<Field, Long>