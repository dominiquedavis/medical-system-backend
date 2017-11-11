package com.medicalsystem.controller

import com.medicalsystem.model.ApplicationUser
import com.medicalsystem.service.ApplicationUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class UserController @Autowired constructor(val userService: ApplicationUserService) {

    @GetMapping("api/users")
    fun getUsers(): List<ApplicationUser> = userService.getAll()

    @GetMapping("api/users/statusList")
    fun getStatusList(): List<String> = listOf("accepted", "waiting")

    @PostMapping("api/users/{userId}/status")
    fun updateStatus(@PathVariable userId: Long, @RequestBody status: String): Boolean
            = userService.updateStatus(userId, status)

    @PostMapping("api/users/{userId}/admin")
    fun updateAdminRights(@PathVariable userId: Long, @RequestBody admin: Boolean): Boolean
            = userService.updateAdminRights(userId, admin)

    @DeleteMapping("api/users/{userId}")
    fun deleteUser(@PathVariable userId: Long): Boolean
            = userService.deleteUser(userId)

    @PostMapping("api/auth/register")
    fun register(@RequestBody user: ApplicationUser): Boolean = userService.register(user)

}