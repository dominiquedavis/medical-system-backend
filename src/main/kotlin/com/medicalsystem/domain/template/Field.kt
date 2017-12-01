package com.medicalsystem.domain.template

import com.medicalsystem.domain.id.LongIdComparableEntity
import javax.persistence.*

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["name", "colIndex"])])
class Field(
    var name: String = "",
    var type: FieldType? = null,
    var colIndex: Int = -1,
    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    var possibleValues: MutableSet<ValueOption> = mutableSetOf(),
    @ManyToOne(fetch = FetchType.LAZY)
    var section: Section? = null
) : LongIdComparableEntity() {

    fun addPossibleValue(option: ValueOption) =
        possibleValues.add(option)

    override fun toString(): String =
        "Field(id='$id', name='$name', type='$type', colIndex='$colIndex')"
}