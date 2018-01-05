package com.medicalsystem.converter.valuestodto

import com.medicalsystem.domain.Patient

/**
 * An interface for converting model objects to DTO objects and filling them with values for a given Patient.
 */
interface ValuesConverter<in S, out T> {

    fun convert(source: S, patient: Patient): T

    fun convertAll(sources: Iterable<S>, patient: Patient): List<T> =
            sources.map { convert(it, patient) }
}