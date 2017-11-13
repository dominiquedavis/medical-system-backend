package com.medicalsystem.factory

import com.medicalsystem.model.Patient

/**
 * Methods for creating DTO from model objects and vice versa
 *
 * @param T DTO type
 * @param U model type
 */
interface DTOFactory<T, U> {
    fun toDTO(us: List<U>, patient: Patient?): List<T> = us.map { toDTO(it, patient) }
    fun toDTO(u: U, patient: Patient?): T
}