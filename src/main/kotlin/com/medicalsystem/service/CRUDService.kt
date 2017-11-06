package com.medicalsystem.service

interface CRUDService<T, in ID> {
    fun getAll(): List<T>
    fun getById(id: ID): T?
    fun exists(id: ID): Boolean
    fun save(t: T): T
    fun delete(id: ID)
    fun deleteAll()
}