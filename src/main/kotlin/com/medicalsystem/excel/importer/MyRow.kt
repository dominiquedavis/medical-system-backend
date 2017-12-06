package com.medicalsystem.excel.importer

import com.medicalsystem.util.getValueAsString
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row

class MyRow(private val row: Row) : Row by row {
    val index: Int
        get() = row.sheet.indexOf(row)

    val patientId: String = initPatientId()

    private fun initPatientId(): String {
        val idCell: Cell = row.iterator().next()
        val patientId = idCell.getValueAsString()
        if (patientId == "")
            throw IllegalArgumentException("Patient ID cannot be empty (row: $index)")
        return patientId
    }
}