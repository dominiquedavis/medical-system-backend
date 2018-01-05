package com.medicalsystem.domain.value

import com.medicalsystem.domain.dto.FieldDTO
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
}