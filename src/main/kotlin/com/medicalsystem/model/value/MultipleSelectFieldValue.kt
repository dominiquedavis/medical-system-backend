package com.medicalsystem.model.value

import javax.persistence.*

@Entity
@Table(name = "MULTIPLE_SELECT_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class MultipleSelectFieldValue : FieldValue<List<String>>() {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "MULTIPLE_VALUES")
    @Column(name = "VALUE")
    override var value: List<String> = emptyList()

    override fun setValueFromString(s: String) {
        val values = mutableListOf<String>()
        var str = s

        if (s.endsWith("11")) {
            values.add("11")
            str = s.substring(0, s.length - "11".length)
        }

        str.toCharArray()
                .mapTo(values) { it.toString() }

        this.value = values
    }

    fun setValuesByMapValues(mapValues: List<String>) {
        val values: MutableList<String> = mutableListOf()
        field?.let {
            it.possibleValues.forEach { key, value ->
                if (mapValues.contains(value))
                    values.add(key)
            }
        }
        this.value = values
    }
}