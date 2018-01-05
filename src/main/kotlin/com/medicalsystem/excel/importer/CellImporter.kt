package com.medicalsystem.excel.importer

import com.medicalsystem.domain.Patient
import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.value.FieldValue
import com.medicalsystem.excel.getAsString
import com.medicalsystem.excel.importer.result.ImportError
import com.medicalsystem.service.FieldValueService
import org.apache.poi.ss.usermodel.Cell
import org.springframework.stereotype.Component

@Component
class CellImporter(private val fieldValueService: FieldValueService) {

    fun importCell(cell: Cell, formSheet: FormSheet, patient: Patient): List<ImportError> {
        // Get field corresponding to this cell from the cached fields
        val field: Field = formSheet.fieldsIndices[cell.columnIndex] ?:
                // Return error if no field found with a given index
                return listOf(ImportError.noFieldWithColumnIndex(formSheet, cell))

        // Create FieldValue object of a corresponding name
        val fieldValue: FieldValue<*> = FieldValue.createInstanceByFieldType(field.type)
        fieldValue.field = field
        fieldValue.patient = patient

        // Set value property of the FieldValue object and return possible errors
        val errorsFromSettingValue = setValue(fieldValue, cell, formSheet)
        if (errorsFromSettingValue.isNotEmpty()) {
            return errorsFromSettingValue
        }

        // No errors here
        return emptyList()
    }

    private fun setValue(fieldValue: FieldValue<*>, cell: Cell, formSheet: FormSheet): List<ImportError> {
        val valueAsString = cell.getAsString()
        val errors = mutableListOf<ImportError>()

        try {
            fieldValue.setValueProperty(valueAsString)
        } catch (e: Exception) {
            errors.add(ImportError.errorSettingValue(formSheet, cell, fieldValue.field, valueAsString, e))
        }

        // Persist FieldValue
        fieldValueService.save(fieldValue)

        return errors
    }
}