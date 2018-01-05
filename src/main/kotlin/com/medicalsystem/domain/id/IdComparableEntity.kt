package com.medicalsystem.domain.id

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*

/**
 * An abstract class providing an ID and methods for comparing entities using this ID.
 * Every managed entity should extend this class.
 *
 * @param ID name of the ID formField
 */
abstract class IdComparableEntity<ID>(@JsonIgnore var defaultId: ID? = null) {
    abstract var id: ID

    override fun equals(other: Any?): Boolean =
            if (other is IdComparableEntity<*>) {
                id != defaultId && Objects.equals(id, other.id)
            } else {
                false
            }

    override fun hashCode(): Int = 31
}