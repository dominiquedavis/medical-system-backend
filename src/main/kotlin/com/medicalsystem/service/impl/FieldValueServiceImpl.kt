package com.medicalsystem.service.impl

import com.medicalsystem.model.value.FieldValue
import com.medicalsystem.repository.value.FieldValueRepository
import com.medicalsystem.service.FieldValueService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FieldValueServiceImpl @Autowired constructor(
        _saveRepository: FieldValueRepository<FieldValue<*>>,
        _readRepositories: MutableList<FieldValueRepository<out FieldValue<*>>>) : FieldValueService {

    val saveRepository: FieldValueRepository<FieldValue<*>> = _saveRepository
    val readRepositories: List<FieldValueRepository<out FieldValue<*>>>
            = _readRepositories.filter { repository -> repository != saveRepository }

    override fun getAll(): List<FieldValue<*>> =
            readRepositories
                    .map { it.findAll() }
                    .flatMap { it.asIterable() }


    override fun getById(id: Long): FieldValue<*>? =
            readRepositories
                    .mapNotNull { it.findOne(id) }
                    .firstOrNull()

    override fun exists(id: Long): Boolean =
            readRepositories
                    .map { it.exists(id) }
                    .any{ it }

    override fun save(t: FieldValue<*>): FieldValue<*>
            = saveRepository.save(t)

    override fun delete(id: Long) =
            readRepositories.forEach { repository ->
                if (repository.exists(id)) {
                    repository.delete(id)
                }
            }

    override fun deleteAll()
            = readRepositories.forEach { it.deleteAll() }
}