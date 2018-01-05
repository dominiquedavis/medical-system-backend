package com.medicalsystem.repository.template

import com.medicalsystem.domain.template.Field
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FieldRepository : JpaRepository<Field, Long> {

    @Query("SELECT MAX(f.columnIdx) FROM Field f")
    fun findMaxColIndex(): Int
}