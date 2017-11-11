package com.medicalsystem.factory

interface DTOFactory<T, U> {
    fun createEmptyDTO(us: List<U>): List<T>
    fun createEmptyDTO(u: U): T
    fun createEmptyFromDTO(ts: List<T>): List<U>
    fun createEmptyFromDTO(t: T): U
}