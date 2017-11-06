package com.medicalsystem.service

import org.springframework.data.jpa.repository.JpaRepository
import java.io.Serializable

abstract class DefaultCRUDService<T, ID : Serializable, out U : JpaRepository<T, ID>>(val repository: U) : CRUDService<T, ID> {
    override fun getAll(): List<T> = repository.findAll()
    override fun getById(id: ID): T? = repository.getOne(id)
    override fun exists(id: ID): Boolean = repository.exists(id)
    override fun save(t: T): T = repository.save(t)
    override fun delete(id: ID) = repository.delete(id)
    override fun deleteAll() = repository.deleteAll()
}