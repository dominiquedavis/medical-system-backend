package com.medicalsystem.domain.template

import com.medicalsystem.domain.id.LongIdComparableEntity
import javax.persistence.Entity

/**
 * An entity representing a key-value mapping from the key in the excel file (e.g. '1')
 * to the dictionary value of this key (e.g. 'Non-smoking').
 */
@Entity
class Option(var key: String = "", var value: String = "") : LongIdComparableEntity() {

    override fun toString(): String =
            "Option(id=$id, key='$key', value='$value')"
}