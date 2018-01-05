package com.medicalsystem.domain.template

import com.medicalsystem.domain.id.LongIdComparableEntity
import javax.persistence.*
import javax.transaction.NotSupportedException

/**
 * An entity class representing a single column in an excel file.
 * Every Field is a part of some Section entity.
 */
@Entity
class Field(
        var name: String = "",
        var type: FieldType = FieldType.TEXT,
        var columnIdx: Int = -1,

        @ManyToOne(fetch = FetchType.LAZY)
        var section: Section? = null,

        //@OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
        @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        var possibleValues: MutableSet<Option> = mutableSetOf()

) : LongIdComparableEntity() {

    fun addPossibleValue(option: Option) {
        if (type != FieldType.SELECT && type != FieldType.MULTIPLE_SELECT) {
            throw NotSupportedException("Cannot add possible values to a non-select field name")
        }

        possibleValues.add(option)
    }

    override fun toString(): String =
            "Field(id=$id, name='$name', name='$type', possibleValues='$possibleValues')"
}