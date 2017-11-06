package com.medicalsystem.repository

import com.medicalsystem.model.Form
import org.springframework.data.jpa.repository.JpaRepository

interface FormRepository : JpaRepository<Form, Long> {
    fun findBySheetIndex(sheetIndex: Int): Form?
}