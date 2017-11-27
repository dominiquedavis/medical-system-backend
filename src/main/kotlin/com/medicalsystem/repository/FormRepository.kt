package com.medicalsystem.repository

import com.medicalsystem.model.Form
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FormRepository : JpaRepository<Form, Long> {
    fun findBySheetIndex(sheetIndex: Int): Form?
    @Query("SELECT f.name FROM Form f")
    fun findAllFormNames(): List<String>
}