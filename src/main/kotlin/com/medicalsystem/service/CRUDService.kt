package com.medicalsystem.service

import org.springframework.data.jpa.repository.JpaRepository
import java.io.Serializable
import javax.persistence.EntityNotFoundException

/**
 * A generic class providing methods for basic CRUD operations.
 *
 * @param T  name of the managed entity
 * @param ID name of the ID of the managed entity
 */
open class CRUDService<T, in ID : Serializable>(private val repository: JpaRepository<T, ID>) {

    fun findAll(): List<T> =
            repository.findAll()

    fun exists(id: ID): Boolean =
            repository.exists(id)

    fun findByID(id: ID): T? =
            if (exists(id)) {
                repository.getOne(id)
            } else {
                null
            }

    fun count(): Long =
            repository.count()

    fun <S : T> save(s: S): S =
            repository.save(s)

    fun <S : T> saveAll(ss: Iterable<S>): Iterable<S> =
            repository.save(ss)

    fun deleteByID(id: ID) =
            if (exists(id)) {
                repository.delete(id)
            } else {
                throw EntityNotFoundException("$id")
            }

    fun deleteAll() =
            repository.deleteAll()
}