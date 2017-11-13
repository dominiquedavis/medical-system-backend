package com.medicalsystem.factory

/**
 * Methods for creating DTO from model objects and vice versa
 *
 * @param T DTO type
 * @param U model type
 */
interface DTOFactory<T, U> {
    fun toDTO(us: List<U>, patientId: String?): List<T> = us.map { toDTO(it, patientId) }
    fun toDTO(u: U, patientId: String?): T
}