package com.medicalsystem.domain.id

import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class StringIdComparableEntity(
        @Id
        override var id: String = ""
) : IdComparableEntity<String>(id)