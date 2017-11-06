package com.medicalsystem.excel.importer

import com.medicalsystem.model.Field
import com.medicalsystem.model.Form
import com.medicalsystem.model.Patient
import com.medicalsystem.service.PatientService
import com.medicalsystem.util.ExcelUtils
import com.medicalsystem.util.logger
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RowImporter @Autowired constructor(
        val patientService: PatientService,
        val cellImporter: CellImporter) {

    fun importToDatabase(row: Row, form: Form, fields: Map<Int, Field>, maxNumberOfCells: Int) {
        if (row.physicalNumberOfCells == 0) {
            logger().warn("No cells found in row: ${row.sheet.indexOf(row)}")
            return
        }

        val patient: Patient = patientFromRow(row, form)

        (1 until maxNumberOfCells)
                .map { row.getCell(it) ?: row.createCell(it) }
                .forEach { cellImporter.importToDatabase(it, patient, fields) }
    }

    fun patientFromRow(row: Row, form: Form): Patient {
        val idCell: Cell = row.iterator().next()
        val patientId: String = ExcelUtils.asString(idCell)
        if (patientId.isEmpty()) throw ExcelImportException("Patient ID is empty")
        return patientService.create(patientId, form) ?:
                throw ExcelImportException("Patient exists with ID: $patientId")
    }
}