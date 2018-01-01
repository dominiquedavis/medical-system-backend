package com.medicalsystem.excel.importer

import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.template.Form
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet

class MySheet(sheet: Sheet, val form: Form) : Sheet by sheet {
    val index: Int
        get() = workbook.getSheetIndex(this)

    val numberOfHeaderRows: Int
        get() = 2

    val name: String
        get() = form.name

    val fieldsIndices: Map<Int, Field> = initFieldsIndices()
    val maxNumberOfCells: Int = initMaxNumberOfCells()

    val containsDataRows: Boolean
        get() = physicalNumberOfRows > numberOfHeaderRows

    private fun initFieldsIndices(): Map<Int, Field> {
        val fields: List<Field> = form.sections.flatMap { it.fields }
        return fields.associateBy { it.colIndex }
    }

    private fun initMaxNumberOfCells(): Int {
        val it: Iterator<Row> = rowIterator()
        it.next()
        return it.next().lastCellNum.toInt()
    }
}