package com.medicalsystem.controller

import com.medicalsystem.model.ApplicationUser
import com.medicalsystem.service.ApplicationUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController @Autowired constructor(val userService: ApplicationUserService) {

    @GetMapping("api/users")
    fun getUsers(): List<ApplicationUser> = userService.getAll()

    @PostMapping("api/auth/register")
    fun register(@RequestBody user: ApplicationUser): Boolean = userService.register(user)
}