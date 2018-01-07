package com.medicalsystem.domain.value

import com.medicalsystem.domain.dto.FieldDTO
import com.medicalsystem.domain.report.ConditionType
import com.medicalsystem.domain.report.ReportField
import com.medicalsystem.domain.template.Option
import com.medicalsystem.util.logger
import org.apache.poi.ss.usermodel.Cell
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToOne

@Entity
class SelectFieldValue(

        @OneToOne(fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
        override var value: Option? = null

) : FieldValue<Option?>() {

    override fun setValueProperty(valueAsString: String) {
        // Empty string means no value selected
        if (valueAsString == "") {
            return
        }

        this.value = getPossibleValueByKey(valueAsString)
    }

    override fun fillCellWithValue(cell: Cell) {
        value?.let {
            cell.setCellValue(it.key)
            return
        }
        cell.setCellValue("")
    }

    override fun setDtoValues(fieldDTO: FieldDTO) {
        fieldDTO.values = listOf(value?.value ?: "")
    }

    override fun setDtoPossibleValues(fieldDTO: FieldDTO) {
        fieldDTO.possibleValues = field?.possibleValues?.map { it.value } ?: emptyList()
    }

    override fun updateValue(values: List<Any>) {
        this.value = getPossibleValueByValue(values.first() as String)
    }

    override fun meetsCriteria(reportField: ReportField): Boolean {
        val condValues: List<String> = reportField.conditionValue

        if (condValues.isEmpty()) {
            throw IllegalArgumentException("Provided list is empty: $reportField")
        }

        if (this.value == null) {
            logger().error("FieldValue is null")
        }

        val stringValue = this.value!!.value

        when (reportField.conditionType) {
            ConditionType.EQUAL -> {
                val arg = condValues.first()
                return stringValue == arg
            }
            ConditionType.BIGGER -> {
                val arg = condValues.first()
                return stringValue > arg
            }
            ConditionType.SMALLER -> {
                val arg = condValues.first()
                return stringValue < arg
            }
            ConditionType.BETWEEN -> {
                if (condValues.size < 2) {
                    throw IllegalArgumentException("Not enough condition values (should be 2): is ${condValues.size}")
                }

                val arg1 = condValues[0]
                val arg2 = condValues[1]

                return (stringValue in arg1..arg2) || (stringValue in arg2..arg1)
            }
            ConditionType.CONTAINS -> {
                val arg = condValues.first()
                return stringValue.contains(arg)
            }
            ConditionType.NOT -> {
                val arg = condValues.first()
                return stringValue != arg
            }
            null -> throw IllegalStateException("ConditionType is null")
        }
    }

    override fun compareToOther(other: FieldValue<*>): Int =
            if (other is SelectFieldValue) {
                this.value?.value?.compareTo(other.value?.value ?: "") ?: 0
            } else {
                0
            }
}