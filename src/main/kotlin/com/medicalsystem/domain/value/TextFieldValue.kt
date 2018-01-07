package com.medicalsystem.domain.value

import com.medicalsystem.domain.dto.FieldDTO
import com.medicalsystem.domain.report.ConditionType.EQUAL
import com.medicalsystem.domain.report.ConditionType.BIGGER
import com.medicalsystem.domain.report.ConditionType.SMALLER
import com.medicalsystem.domain.report.ConditionType.BETWEEN
import com.medicalsystem.domain.report.ConditionType.CONTAINS
import com.medicalsystem.domain.report.ReportField
import com.medicalsystem.util.logger
import org.apache.poi.ss.usermodel.Cell
import javax.persistence.Entity

@Entity
class TextFieldValue(override var value: String? = null) : FieldValue<String?>() {

    override fun setValueProperty(valueAsString: String) {
        this.value = valueAsString
    }

    override fun fillCellWithValue(cell: Cell) {
        cell.setCellValue(this.value)
    }

    override fun setDtoValues(fieldDTO: FieldDTO) {
        fieldDTO.values = listOf(value ?: "")
    }

    override fun setDtoPossibleValues(fieldDTO: FieldDTO) {
        fieldDTO.possibleValues = emptyList()
    }

    override fun updateValue(values: List<Any>) {
        this.value = values.first() as String
    }

    override fun meetsCriteria(reportField: ReportField): Boolean {
        val condValues: List<String> = reportField.conditionValue

        if (condValues.isEmpty()) {
            throw IllegalArgumentException("Provided list is empty: $reportField")
        }

        if (this.value == null) {
            logger().error("FieldValue is null")
        }

        when (reportField.conditionType) {
            EQUAL -> {
                val arg = condValues.first()
                return this.value == arg
            }
            BIGGER -> {
                val arg = condValues.first()
                return this.value!! > arg
            }
            SMALLER -> {
                val arg = condValues.first()
                return this.value!! < arg
            }
            BETWEEN -> {
                if (condValues.size < 2) {
                    throw IllegalArgumentException("Not enough condition values (should be 2): is ${condValues.size}")
                }

                val arg1 = condValues[0]
                val arg2 = condValues[1]
                val value = this.value!!

                return (value in arg1..arg2) || (value in arg2..arg1)
            }
            CONTAINS -> {
                val arg = condValues.first()
                return this.value!!.contains(arg)
            }
            null -> throw IllegalStateException("ConditionType is null")
        }
    }
}