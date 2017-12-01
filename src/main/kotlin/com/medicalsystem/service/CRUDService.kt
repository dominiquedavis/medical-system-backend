package com.medicalsystem.service

import org.springframework.data.jpa.repository.JpaRepository
import java.io.Serializable

abstract class CRUDService<T, ID : Serializable>(val repository: JpaRepository<T, ID>) {
    fun getAll(): List<T> = repository.findAll()
    fun getByID(id: ID): T? = if (exists(id)) repository.getOne(id) else null
    fun exists(id: ID): Boolean = repository.exists(id)
    fun save(t: T): T = repository.save(t)
    fun delete(id: ID) = if (exists(id)) repository.delete(id) else Unit
    fun deleteAll() = repository.deleteAll()
}