package com.medicalsystem.domain.value

import com.medicalsystem.domain.dto.FieldDTO
import com.medicalsystem.domain.report.ConditionType
import com.medicalsystem.domain.report.ReportField
import com.medicalsystem.util.logger
import org.apache.poi.ss.usermodel.Cell
import javax.persistence.Entity

@Entity
class NumberFieldValue(override var value: Double? = null) : FieldValue<Double?>() {

    override fun setValueProperty(valueAsString: String) {
        this.value = valueAsString.toDouble()
    }

    override fun fillCellWithValue(cell: Cell) {
        if (value == null) {
            cell.setCellValue("")
        } else {
            cell.setCellValue(value!!)
        }
    }

    override fun setDtoValues(fieldDTO: FieldDTO) {
        if (value == null) {
            fieldDTO.values = listOf("")
        } else {
            fieldDTO.values = listOf(value!!)
        }
    }

    override fun setDtoPossibleValues(fieldDTO: FieldDTO) {
        fieldDTO.possibleValues = emptyList()
    }

    override fun updateValue(values: List<Any>) {
        val value = values.first()

        this.value =
                if (value is Int) {
                    value.toDouble()
                } else {
                    value as Double
                }
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
            ConditionType.EQUAL -> {
                val arg = condValues.first().toDouble()
                return this.value == arg
            }
            ConditionType.BIGGER -> {
                val arg = condValues.first().toDouble()
                return this.value!! > arg
            }
            ConditionType.SMALLER -> {
                val arg = condValues.first().toDouble()
                return this.value!! < arg
            }
            ConditionType.BETWEEN -> {
                if (condValues.size < 2) {
                    throw IllegalArgumentException("Not enough condition values (should be 2): is ${condValues.size}")
                }

                val arg1 = condValues[0].toDouble()
                val arg2 = condValues[1].toDouble()
                val value = this.value!!

                return (value in arg1..arg2) || (value in arg2..arg1)
            }
            ConditionType.CONTAINS -> {
                return false
            }
            null -> throw IllegalStateException("ConditionType is null")
        }
    }
}