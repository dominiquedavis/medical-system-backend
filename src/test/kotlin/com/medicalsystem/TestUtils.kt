package com.medicalsystem

import com.medicalsystem.domain.id.IdComparableEntity
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull

fun <T : IdComparableEntity<*>> testSavingEntity(entity: T?) {
    assertNotNull(entity)
    assertNotEquals(entity?.defaultId, entity?.id)
}