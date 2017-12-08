package com.medicalsystem.model.value

import com.medicalsystem.model.report.ConditionType
import com.medicalsystem.model.report.ReportField
import org.apache.commons.lang3.math.NumberUtils
import org.apache.poi.ss.usermodel.Cell
import javax.persistence.*

@Entity
@Table(name = "NUMBER_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class NumberFieldValue : FieldValue<Double>() {

    @Column(name = "VALUE")
    override var value: Double = 0.0

    override fun setValueFromString(s: String) {
        this.value =
                if (NumberUtils.isCreatable(s)) {
                    s.toDouble()
                } else {
                    0.0
                }
    }

    override fun createCellValue(cell: Cell) {
        cell.setCellValue(value)
    }

    override fun fullfills(reportField: ReportField): Boolean =
        when (reportField.conditionType) {
            ConditionType.EQUAL ->
                value == reportField.conditionValue[0].toDouble()
            ConditionType.BIGGER ->
                value > reportField.conditionValue[0].toDouble()
            ConditionType.SMALLER ->
                value < reportField.conditionValue[0].toDouble()
            ConditionType.BETWEEN ->
                reportField.conditionValue[0].toDouble() < value && value < reportField.conditionValue[0].toDouble()
            ConditionType.CONTAINS ->
                reportField.conditionValue.map { it.toDouble() }.contains(value)
            null -> true
        }
}