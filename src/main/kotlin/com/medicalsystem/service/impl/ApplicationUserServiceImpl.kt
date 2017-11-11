package com.medicalsystem.service.impl

import com.medicalsystem.model.ApplicationUser
import com.medicalsystem.repository.ApplicationUserRepository
import com.medicalsystem.service.ApplicationUserService
import com.medicalsystem.util.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ApplicationUserServiceImpl @Autowired constructor(
        val userRepository: ApplicationUserRepository,
        val passwordEncoder: BCryptPasswordEncoder) : ApplicationUserService(userRepository) {

    override fun getByUsername(username: String): ApplicationUser? = userRepository.findByUsername(username)

    override fun existsByUsername(username: String): Boolean = (getByUsername(username) != null)

    override fun updateStatus(userId: Long, status: String): Boolean {
        val user: ApplicationUser = getById(userId) ?: return false
        user.status = status
        save(user)
        logger().info("Status updated for user ${user.username}: ${user.status}")
        return true
    }

    override fun updateAdminRights(userId: Long, admin: Boolean): Boolean {
        val user: ApplicationUser = getById(userId) ?: return false
        user.admin = admin
        save(user)
        logger().info("Admin rights updated for user ${user.username}: ${user.status}")
        return true
    }

    override fun deleteUser(userId: Long): Boolean =
            if (exists(userId)) {
                logger().info("User not found with ID: $userId")
                false
            } else {
                delete(userId)
                true
            }

    override fun register(user: ApplicationUser): Boolean =
            if (!existsByUsername(user.username)) {
                encryptPassword(user)
                save(user)
                true
            } else {
                logger().info("User exists: '${user.username}'")
                false
            }

    private fun encryptPassword(user: ApplicationUser) {
        user.password = passwordEncoder.encode(user.password)
    }
}