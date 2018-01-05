package com.medicalsystem.excel.importer

import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.template.Form
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet

/**
 * An extension class for Sheet.
 * It binds the sheet with the specified Form entity providing helper methods.
 */
class FormSheet(sheet: Sheet, val form: Form) : Sheet by sheet {
    val index: Int = workbook.getSheetIndex(this)
    val numberOfHeaderRows: Int = 2
    val containsNoDataRows: Boolean = physicalNumberOfRows <= numberOfHeaderRows
    val maxNumberOfCells: Int = initMaxNumberOfCells()

    /**
     * A mapping of formField index to actual Field object.
     * For caching purposes.
     */
    val fieldsIndices: Map<Int, Field> = initFieldsIndices()

    private fun initMaxNumberOfCells(): Int {
        val it: Iterator<Row> = rowIterator()
        it.next()
        return it.next().lastCellNum.toInt()
    }

    private fun initFieldsIndices(): Map<Int, Field> {
        // Get all fields in the form
        val allFields: List<Field> = form.sections.flatMap { it.fields }

        return allFields.associateBy { it.columnIdx }
    }
}