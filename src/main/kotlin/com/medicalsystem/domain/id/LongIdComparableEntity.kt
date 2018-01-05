package com.medicalsystem.domain.id

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class LongIdComparableEntity(@Id @GeneratedValue override var id: Long = 0L) : IdComparableEntity<Long>(id)