package com.medicalsystem.repository.template

import com.medicalsystem.domain.template.Form
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FormRepository : JpaRepository<Form, Long> {

    fun findByName(name: String): Form?

    fun findBySheetName(sheetName: String): Form?

    @Query("SELECT f.name FROM Form f")
    fun findAllFormNames(): List<String>
}