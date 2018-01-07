package com.medicalsystem.domain.value

import com.medicalsystem.domain.dto.FieldDTO
import com.medicalsystem.domain.report.ConditionType
import com.medicalsystem.domain.report.ReportField
import com.medicalsystem.domain.template.Option
import com.medicalsystem.util.logger
import org.apache.poi.ss.usermodel.Cell
import javax.naming.OperationNotSupportedException
import javax.persistence.*

@Entity
class MultipleSelectFieldValue(
        @JoinTable(name = "field_value_option",
                joinColumns = [(JoinColumn(name = "field_value_id"))],
                inverseJoinColumns = [(JoinColumn(name = "value_option_id"))]
        )
        @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
        override var value: MutableSet<Option> = mutableSetOf()

) : FieldValue<MutableSet<Option>>() {

    /**
     * In case of multiple select fields, the keys of the possible values are basically
     * one-character strings or "11". Should the string contain "11" key, it will always
     * be at the end, thus allowing it to be identified easily.
     * E.g. "12611" maps to "1", "2", "6", "11".
     */
    override fun setValueProperty(valueAsString: String) {
        // Empty string means no values selected
        if (valueAsString == "") {
            return
        }

        // Check for "11" key at the end of the string
        val length =
                if (valueAsString.endsWith("11")) {
                    addByKey("11")
                    valueAsString.length - "11".length
                } else {
                    valueAsString.length
                }

        // Iterate over chars in the string and map them to proper Option objects
        (0 until length)
                .map { valueAsString[it].toString() }
                .forEach { addByKey(it) }
    }

    override fun fillCellWithValue(cell: Cell) {
        // Get all the keys as a single string.
        // Make sure the "11" key is at the end.
        val stringValue = value.map { it.key }
                .sortedWith(compareBy({ it.length }, { it }))
                .joinToString("")

        cell.setCellValue(stringValue)
    }

    override fun setDtoValues(fieldDTO: FieldDTO) {
        fieldDTO.values = value.map { it.value }
    }

    override fun setDtoPossibleValues(fieldDTO: FieldDTO) {
        fieldDTO.possibleValues = field?.possibleValues?.map { it.value } ?: emptyList()
    }

    override fun updateValue(values: List<Any>) {
        this.value = values.map { getPossibleValueByValue(it as String) }.toMutableSet()
    }

    override fun meetsCriteria(reportField: ReportField): Boolean {
        val condValues: List<String> = reportField.conditionValue

        if (condValues.isEmpty()) {
            throw IllegalArgumentException("Provided list is empty: $reportField")
        }

        val stringValues: List<String> = this.value.map { it.value }

        when (reportField.conditionType) {
            ConditionType.EQUAL -> {
                return stringValues == condValues
            }
            ConditionType.BIGGER -> {
                throw OperationNotSupportedException("Cannot apply BIGGER to a multiple select field")
            }
            ConditionType.SMALLER -> {
                throw OperationNotSupportedException("Cannot apply SMALLER to a multiple select field")
            }
            ConditionType.BETWEEN -> {
                throw OperationNotSupportedException("Cannot apply BETWEEN to a multiple select field")
            }
            ConditionType.CONTAINS -> {
                val arg = condValues.first()
                return stringValues.contains(arg)
            }
            ConditionType.NOT -> {
                return stringValues != condValues
            }
            null -> throw IllegalStateException("ConditionType is null")
        }
    }

    override fun compareToOther(other: FieldValue<*>): Int =
            if (other is MultipleSelectFieldValue) {
                this.value.joinToString("") { it.value }
                        .compareTo(other.value.joinToString("") { it.value })
            } else {
                0
            }

    private fun addByKey(key: String) {
        value.add(getPossibleValueByKey(key))
    }
}