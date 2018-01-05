package com.medicalsystem.domain.value

import com.medicalsystem.domain.dto.FieldDTO
import com.medicalsystem.excel.setStyleAsDate
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
}