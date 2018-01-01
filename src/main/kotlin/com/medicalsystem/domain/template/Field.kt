package com.medicalsystem.domain.template

import com.medicalsystem.domain.id.LongIdComparableEntity
import javax.persistence.*

@Entity
class Field(
    var name: String = "",
    var type: FieldType? = null,
    var colIndex: Int = -1,
    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    var possibleValues: MutableSet<ValueOption> = mutableSetOf(),
    @ManyToOne(fetch = FetchType.LAZY)
    var section: Section? = null
) : LongIdComparableEntity() {

    fun addPossibleValue(option: ValueOption) =
        possibleValues.add(option)

    override fun toString(): String =
        "Field(id='$id', name='$name', type='$type', colIndex='$colIndex')"
}