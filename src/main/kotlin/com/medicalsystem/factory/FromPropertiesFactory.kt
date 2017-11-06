package com.medicalsystem.factory

interface FromPropertiesFactory<in T, U> {
    fun createFromProperties(t: T): U
    fun createFromProperties(t: List<T>): List<U>
}