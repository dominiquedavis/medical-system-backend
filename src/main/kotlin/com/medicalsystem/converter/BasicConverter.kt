package com.medicalsystem.converter

@FunctionalInterface
interface BasicConverter<in F, out T> {

    fun convert(from: F): T

    fun convertAll(fromElements: Collection<F>): Collection<T> =
            fromElements.map { convert(it) }
}