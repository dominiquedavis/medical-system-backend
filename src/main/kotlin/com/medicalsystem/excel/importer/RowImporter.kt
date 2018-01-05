package com.medicalsystem.excel.importer

import com.medicalsystem.domain.Patient
import com.medicalsystem.excel.getAsString
import com.medicalsystem.excel.importer.result.ImportError
import com.medicalsystem.service.PatientService
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.springframework.stereotype.Component

@Component
class RowImporter(private val patientService: PatientService, private val cellImporter: CellImporter) {

    fun importRow(row: Row, formSheet: FormSheet): List<ImportError> {
        if (row.physicalNumberOfCells == 0) {
            return listOf(ImportError.noDataCellsInRow(formSheet, row))
        }

        // Extract patient ID
        val patientId = row.getPatientId()
        if (patientId == "") {
            return listOf(ImportError.patientIdEmpty(formSheet, row))
        }

        // Check if patient exists
        if (patientService.exists(patientId)) {
            return listOf(ImportError.patientExists(formSheet, row, patientId))
        }

        // Create and save Patient object
        val patient = patientService.save(Patient(patientId, formSheet.form))

        // Process the rest of the cells
        return (1 until formSheet.maxNumberOfCells)
                // Get cell or create empty cell if one does not exist at given index
                .map { row.getCell(it) ?: row.createCell(it) }
                // Import each cell and aggregate results
                .flatMap { cellImporter.importCell(it, formSheet, patient) }
    }

    private fun Row.getPatientId(): String {
        val cellWithId: Cell = this.iterator().next()
        return cellWithId.getAsString()
    }
}