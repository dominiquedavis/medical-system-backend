package com.medicalsystem.domain.template

import com.medicalsystem.domain.id.LongIdComparableEntity
import javax.persistence.Entity

@Entity
class ValueOption(
    var key: String = "",
    var value: String = ""
) : LongIdComparableEntity() {

    override fun toString(): String = "ValueOption(id='$id', key='$key', value='$value')"
}