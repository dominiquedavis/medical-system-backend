package com.medicalsystem.excel.importer

import com.medicalsystem.factory.FieldValueFactory
import com.medicalsystem.model.Field
import com.medicalsystem.model.Patient
import com.medicalsystem.model.value.FieldValue
import com.medicalsystem.service.FieldValueService
import com.medicalsystem.util.ExcelUtils
import com.medicalsystem.util.logger
import org.apache.poi.ss.usermodel.Cell
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CellImporter @Autowired constructor(val fieldValueService: FieldValueService) {

    fun importToDatabase(cell: Cell, patient: Patient, fields: Map<Int, Field>) {
        val field: Field = fields[cell.columnIndex] ?:
                throw ExcelImportException("Field not found with column index: ${cell.columnIndex}")

        val fieldValue: FieldValue<*> = FieldValueFactory.fromFieldAndStringValue(field, ExcelUtils.asString(cell))
        fieldValueService.save(fieldValue)

        logger().info("[${patient.id}, ${field.name}]: ${fieldValue.value}")
    }
}