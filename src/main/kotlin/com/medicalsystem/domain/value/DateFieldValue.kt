package com.medicalsystem.domain.value

import com.medicalsystem.domain.dto.FieldDTO
import com.medicalsystem.domain.report.ConditionType
import com.medicalsystem.domain.report.ReportField
import com.medicalsystem.excel.setStyleAsDate
import com.medicalsystem.util.logger
import com.medicalsystem.util.stringToLocalDate
import com.medicalsystem.util.toJsonString
import com.medicalsystem.util.toUtilDate
import org.apache.poi.ss.usermodel.Cell
import java.time.LocalDate
import javax.persistence.Entity

/**
 * A FieldValue entity specific for data name.
 * Convertion from java.time.LocalDate to java.sql.Date handled by LocalDateAttributeConverter.
 */
@Entity
class DateFieldValue(override var value: LocalDate? = null) : FieldValue<LocalDate?>() {

    override fun setValueProperty(valueAsString: String) {
        this.value = stringToLocalDate(valueAsString)
    }

    override fun fillCellWithValue(cell: Cell) {
        cell.setStyleAsDate()

        if (value == null) {
            cell.setCellValue("")
        } else {
            cell.setCellValue(value?.toUtilDate())
        }
    }

    override fun setDtoValues(fieldDTO: FieldDTO) {
        fieldDTO.values = listOf(value?.toJsonString() ?: "")
    }

    override fun setDtoPossibleValues(fieldDTO: FieldDTO) {
        fieldDTO.possibleValues = emptyList()
    }

    override fun updateValue(values: List<Any>) {
        this.value = stringToLocalDate(values.first() as String)
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
                val arg = stringToLocalDate(condValues.first())
                return this.value == arg
            }
            ConditionType.BIGGER -> {
                val arg = stringToLocalDate(condValues.first())
                return this.value!! > arg
            }
            ConditionType.SMALLER -> {
                val arg = stringToLocalDate(condValues.first())
                return this.value!! < arg
            }
            ConditionType.BETWEEN -> {
                if (condValues.size < 2) {
                    throw IllegalArgumentException("Not enough condition values (should be 2): is ${condValues.size}")
                }

                val arg1 = stringToLocalDate(condValues[0])
                val arg2 = stringToLocalDate(condValues[1])
                val value = this.value!!

                return (value in arg1..arg2) || (value in arg2..arg1)
            }
            ConditionType.CONTAINS -> {
                return false
            }
            null -> throw IllegalStateException("ConditionType is null")
        }
    }

    override fun compareToOther(other: FieldValue<*>): Int =
            if (other is DateFieldValue) {
                this.value?.compareTo(other.value ?: LocalDate.MAX) ?: 0
            } else {
                0
            }
}