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