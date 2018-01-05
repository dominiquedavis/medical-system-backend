package com.medicalsystem.domain.value

import com.medicalsystem.domain.dto.FieldDTO
import com.medicalsystem.domain.template.Option
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
}