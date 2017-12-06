package com.medicalsystem.excel.importer

import com.medicalsystem.domain.Patient
import com.medicalsystem.excel.result.ImportResult
import com.medicalsystem.service.PatientService
import org.apache.poi.ss.usermodel.Row
import org.springframework.stereotype.Component

@Component
class RowImporter(private val patientService: PatientService,
                  private val cellImporter: CellImporter) {

    fun importToDatabase(_row: Row, sheet: MySheet, result: ImportResult) {
        val row = MyRow(_row)

        if (row.physicalNumberOfCells == 0) {
            result.noDataCellsInRow(row, sheet)
            return
        }

        val patient: Patient? = patientService.create(row.patientId, sheet.form)
        if (patient == null) {
            result.patientIdExists(row, sheet)
            return
        }

        (1 until sheet.maxNumberOfCells)
                .map { row.getCell(it) ?: row.createCell(it) }
                .forEach { cellImporter.importToDatabase(it, row, sheet, patient, result) }
    }
}