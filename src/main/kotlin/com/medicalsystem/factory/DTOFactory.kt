package com.medicalsystem.factory

interface DTOFactory<out T, in U> {
    fun createEmptyDTO(us: List<U>): List<T>
    fun createEmptyDTO(u: U): T
}