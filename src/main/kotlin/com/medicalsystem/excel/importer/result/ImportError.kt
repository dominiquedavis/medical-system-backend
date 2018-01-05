package com.medicalsystem.excel.importer.result

import com.medicalsystem.domain.template.Field
import com.medicalsystem.excel.importer.FormSheet
import com.medicalsystem.exception.*
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row

data class ImportError(
        val sheet: String? = null,
        val row: Int? = null,
        val cell: String? = null,
        val message: String = "",
        val sheetImported: Boolean = false,
        val rowImported: Boolean = false,
        val cellImported: Boolean = false
) {

    companion object {
        fun noDataRowsInSheet(formSheet: FormSheet): ImportError =
                ImportError(sheet = formSheet.sheetName, message = NO_DATA_ROWS_IN_SHEET)

        fun noDataCellsInRow(formSheet: FormSheet, row: Row): ImportError =
                ImportError(sheet = formSheet.sheetName, row = row.getIndex(),
                        message = NO_DATA_CELLS_IN_ROW, sheetImported = true)

        fun patientExists(formSheet: FormSheet, row: Row, patientId: String): ImportError =
                ImportError(sheet = formSheet.sheetName, row = row.getIndex(), cell = "ID",
                        message = PATIENT_EXISTS_WITH_ID + patientId, sheetImported = true)

        fun patientIdEmpty(formSheet: FormSheet, row: Row): ImportError =
                ImportError(sheet = formSheet.sheetName, row = row.getIndex(), cell = "ID",
                        message = PATIENT_ID_EMPTY, sheetImported = true)

        fun noFieldWithColumnIndex(formSheet: FormSheet, cell: Cell): ImportError =
                ImportError(
                        sheet = formSheet.sheetName, row = cell.row.getIndex(), cell = cell.columnIndex.toString(),
                        message = NO_FIELD_WITH_COLUMN_INDEX + cell.columnIndex,
                        sheetImported = true, rowImported = true)

        fun errorSettingValue(formSheet: FormSheet, cell: Cell, field: Field?, valueAsString: String, e: Exception): ImportError =
                ImportError(sheet = formSheet.sheetName, row = cell.row.getIndex(), cell = field?.name,
                        message = INCORRECT_VALUE_IN_CELL + "$valueAsString (${e.message})",
                        sheetImported = true, rowImported = true)

        private fun Row.getIndex(): Int =
                this.sheet.indexOf(this) + 1
    }
}