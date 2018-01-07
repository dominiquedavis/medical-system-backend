package com.medicalsystem.domain.value

import com.medicalsystem.domain.Patient
import com.medicalsystem.domain.dto.FieldDTO
import com.medicalsystem.domain.id.LongIdComparableEntity
import com.medicalsystem.domain.report.ReportField
import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.template.FieldType
import com.medicalsystem.domain.template.FieldType.*
import com.medicalsystem.domain.template.Option
import com.medicalsystem.exception.FIELD_IS_NULL
import com.medicalsystem.exception.INCORRECT_VALUE_IN_CELL
import com.medicalsystem.exception.NO_OPTION_WITH_VALUE
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import javax.persistence.*

/**
 * An abstract class providing common properties for all of the types of formField values.
 * A FieldValue entity represents a single value for a specified Field for a specified Patient.
 *
 * @param T name of the stored value
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class FieldValue<T>(

        @ManyToOne(fetch = FetchType.LAZY)
        var patient: Patient? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        var field: Field? = null

) : LongIdComparableEntity() {

    abstract var value: T

    companion object {
        /**
         * Creates a FieldValue<*> instance based on FieldType
         */
        fun createInstanceByFieldType(fieldType: FieldType): FieldValue<*> =
                when (fieldType) {
                    DATE -> DateFieldValue()
                    TEXT -> TextFieldValue()
                    NUMBER -> NumberFieldValue()
                    SELECT -> SelectFieldValue()
                    MULTIPLE_SELECT -> MultipleSelectFieldValue()
                }
    }

    /**
     * Converts string value to a proper name and sets the 'value' property.
     */
    abstract fun setValueProperty(valueAsString: String)

    /**
     * Creates an excel cell and fills it with the current value.
     *
     * @param row an excel row in which the cell should be created
     */
    fun exportToExcelCell(row: Row) {
        val cellIndex: Int = field?.columnIdx ?: throw IllegalStateException(FIELD_IS_NULL)
        exportToExcelCell(row, cellIndex)
    }

    fun exportToExcelCell(row: Row, cellIndex: Int) {
        val cell: Cell = row.createCell(cellIndex)
        fillCellWithValue(cell)
    }

    /**
     * Fills an excel cell with the current value.
     *
     * @param cell an excel cells which should be filled
     */
    abstract fun fillCellWithValue(cell: Cell)

    /**
     * Sets values property of FieldDTO object upon this.value
     */
    abstract fun setDtoValues(fieldDTO: FieldDTO)

    /**
     * Sets possibleValues property of FieldDTO object upon fields possible values
     */
    abstract fun setDtoPossibleValues(fieldDTO: FieldDTO)

    /**
     * Updates this.value with provided list.
     */
    abstract fun updateValue(values: List<Any>)

    /**
     * Returns an Option with the given key. Throws NoSuchElementException if not found.
     */
    protected fun getPossibleValueByKey(key: String): Option =
            getPossibleValues().find { it.key == key } ?: throw NoSuchElementException(INCORRECT_VALUE_IN_CELL + key)

    /**
     * Returns an Option with the given value. Throws NoSuchElementException if not found.
     */
    protected fun getPossibleValueByValue(value: String): Option =
            getPossibleValues().find { it.value == value } ?: throw NoSuchElementException(NO_OPTION_WITH_VALUE + "$field $value")

    /**
     * Returns true if field value meets contrains of the given ReportField
     */
    abstract fun meetsCriteria(reportField: ReportField): Boolean

    /**
     * Returns a set of possible values of the associated Field.
     * Throws IllegalStateException if the Field is null.
     */
    private fun getPossibleValues(): Set<Option> =
            field?.possibleValues ?: throw IllegalStateException(FIELD_IS_NULL)

    override fun toString(): String =
            "FieldValue(id=$id, formField=${field?.id}, patient='${patient?.id}', value='$value')"

    abstract fun compareToOther(other: FieldValue<*>): Int
}