package com.medicalsystem.factory

interface FromPropertiesFactory<in T, out U> {
    fun createFromProperties(t: T): U
    fun createFromProperties(t: List<T>): List<U>
}