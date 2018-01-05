package com.medicalsystem.domain.value

import com.medicalsystem.domain.dto.FieldDTO
import com.medicalsystem.domain.template.Option
import org.apache.poi.ss.usermodel.Cell
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

    private fun addByKey(key: String) {
        value.add(getPossibleValueByKey(key))
    }
}