package com.medicalsystem.repository

import com.medicalsystem.model.Field
import com.medicalsystem.model.Form
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface FieldRepository : JpaRepository<Field, Long> {
    @Query("SELECT f FROM Field f JOIN f.section s JOIN s.form fo WHERE fo = :form")
    fun findAllByForm(@Param("form") form: Form): List<Field>

    @Query("SELECT MAX(f.columnIndex) FROM Field f")
    fun findMaxColIndex(): Int
}