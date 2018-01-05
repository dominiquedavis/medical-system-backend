package com.medicalsystem.domain.value

import com.medicalsystem.domain.dto.FieldDTO
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
}