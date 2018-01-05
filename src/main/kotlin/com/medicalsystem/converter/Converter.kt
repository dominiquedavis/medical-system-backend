package com.medicalsystem.converter

/**
 * A basic interface for converting entities.
 *
 * @param S name of the source object
 * @param T name of the target object
 */
interface Converter<in S, out T> {

    fun convert(source: S): T

    fun convertAll(sources: Iterable<S>): List<T> =
            sources.map { convert(it) }
}