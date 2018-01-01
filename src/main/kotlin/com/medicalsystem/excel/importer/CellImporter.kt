package com.medicalsystem.excel.importer

import com.medicalsystem.domain.Patient
import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.value.FieldValue
import com.medicalsystem.excel.result.ImportResult
import com.medicalsystem.service.FieldValueService
import com.medicalsystem.util.getValueAsString
import org.apache.poi.ss.usermodel.Cell
import org.springframework.stereotype.Component

@Component
class CellImporter(private val fieldValueService: FieldValueService) {

    fun importToDatabase(cell: Cell, row: MyRow, sheet: MySheet, patient: Patient, result: ImportResult) {
        val field: Field? = sheet.fieldsIndices[cell.columnIndex]
        if (field == null) {
            result.noFieldWithColumnIndex(cell, row, sheet)
            return
        }

        val fieldValue: FieldValue<*> = FieldValue.getInstance(field.type)
        fieldValue.field = field
        fieldValue.patient = patient

        val stringValue = cell.getValueAsString()
        try {
            fieldValue.setValueFromString(stringValue)
        } catch (e: Exception) {
            result.errorConvertingValue(stringValue, e, row, sheet, field)
            return
        }

        fieldValueService.save(fieldValue)
    }
}