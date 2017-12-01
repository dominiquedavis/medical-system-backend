package com.medicalsystem.domain.id

import java.util.*

abstract class IdComparableEntity<ID>(private var defaultId: ID) {
    abstract var id: ID

    override fun equals(other: Any?): Boolean =
        if (other is IdComparableEntity<*>) {
            id != defaultId && Objects.equals(id, other.id)
        } else {
            false
        }

    override fun hashCode(): Int = 31
}