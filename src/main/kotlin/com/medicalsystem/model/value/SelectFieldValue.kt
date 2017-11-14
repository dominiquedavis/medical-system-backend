package com.medicalsystem.model.value

import org.apache.poi.ss.usermodel.Cell
import javax.persistence.*

@Entity
@Table(name = "SELECT_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class SelectFieldValue : FieldValue<String?>() {

    @Column(name = "VALUE")
    override var value: String? = ""

    override fun setValueFromString(s: String) {
        this.value =
                if (field?.possibleValues!!.contains(s)) {
                    s
                } else {
                    null
                }
    }

    fun setValueByMapValue(mapValue: String) {
        field?.let {
            it.possibleValues.forEach { key, value ->
                if (value == mapValue)
                    this.value = key
            }
        }
    }

    override fun createCellValue(cell: Cell) {
        cell.setCellValue(value)
    }
}