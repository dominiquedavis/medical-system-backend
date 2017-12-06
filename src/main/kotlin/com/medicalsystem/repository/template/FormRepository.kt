package com.medicalsystem.repository.template

import com.medicalsystem.domain.template.Form
import org.springframework.data.jpa.repository.JpaRepository

interface FormRepository : JpaRepository<Form, Long> {

    fun findBySheetIndex(sheetIndex: Int): Form?
}