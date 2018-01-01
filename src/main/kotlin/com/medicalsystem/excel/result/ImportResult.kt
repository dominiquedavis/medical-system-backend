package com.medicalsystem.excel.result

import com.medicalsystem.domain.template.Field
import com.medicalsystem.excel.importer.MyRow
import com.medicalsystem.excel.importer.MySheet
import org.apache.poi.ss.usermodel.Cell

data class ImportResult(val errors: MutableList<ErrorDetails> = mutableListOf()) {

    fun noDataRowsInSheet(sheet: MySheet) =
            errors.add(ErrorDetails(
                    sheet = sheet.name,
                    row = null,
                    cell = null,
                    message = "Arkusz nie zawiera rekordów z danymi",
                    sheetImported = false,
                    rowImported = false,
                    cellImported = false
            ))

    fun noDataCellsInRow(row: MyRow, sheet: MySheet) =
            errors.add(ErrorDetails(
                    sheet = sheet.name,
                    row = row.index + 1,
                    cell = null,
                    message = "Rekord nie zawiera komórek z danymi",
                    sheetImported = true,
                    rowImported = false,
                    cellImported = false
            ))

    fun patientIdExists(row: MyRow, sheet: MySheet) =
            errors.add(ErrorDetails(
                    sheet = sheet.name,
                    row = row.index + 1,
                    cell = "ID",
                    message = "Pacjent o podanym id już istnieje: '${row.patientId}'",
                    sheetImported = true,
                    rowImported = false,
                    cellImported = false
            ))

    fun noFieldWithColumnIndex(cell: Cell, row: MyRow, sheet: MySheet) =
            errors.add(ErrorDetails(
                    sheet = sheet.name,
                    row = row.index + 1,
                    cell = cell.columnIndex.toString(),
                    message = "Brak pola o indeksie kolumny: '${cell.columnIndex}'",
                    sheetImported = true,
                    rowImported = true,
                    cellImported = false
            ))

    fun errorConvertingValue(stringValue: String, e: Exception, row: MyRow, sheet: MySheet, field: Field) =
            errors.add(ErrorDetails(
                    sheet = sheet.name,
                    row = row.index + 1,
                    cell = field.name,
                    message = "Niepoprawna wartość w komórce: '$stringValue' (${e.message})",
                    sheetImported = true,
                    rowImported = true,
                    cellImported = false
            ))
}