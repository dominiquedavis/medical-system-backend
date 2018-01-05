package com.medicalsystem.controller

import com.medicalsystem.domain.ApplicationUser
import com.medicalsystem.service.ApplicationUserService
import org.springframework.web.bind.annotation.*

@RestController
class UserController(private val userService: ApplicationUserService) {

    /**
     * Returns all users registered in the system.
     */
    @GetMapping("api/users")
    fun getUsers(): List<ApplicationUser> =
            userService.findAll()

    /**
     * Returns all possible string values of the status.
     */
    @GetMapping("api/users/statusList")
    fun getStatusList(): List<String> =
            listOf("accepted", "waiting")

    /**
     * Changes status of a given user.
     *
     * @param userId ID of the user whose status should be changed
     * @param status string value of the new status
     */
    @PostMapping("api/users/{userId}/status")
    fun updateStatus(@PathVariable userId: Long, @RequestBody status: String): Boolean =
            userService.updateStatus(userId, status)

    /**
     * Changes admin rights of a given user.
     *
     * @param userId ID of the user whose admin rights should be changed
     * @param admin  true if the user should be admin, false otherwise
     */
    @PostMapping("api/users/{userId}/admin")
    fun updateAdminRights(@PathVariable userId: Long, @RequestBody admin: Boolean): Boolean =
            userService.updateAdminRights(userId, admin)

    /**
     * Deletes a user.
     *
     * @param userId ID of the user to be deleted
     */
    @DeleteMapping("api/users/{userId}")
    fun deleteUser(@PathVariable userId: Long): Boolean =
            userService.deleteUser(userId)

    /**
     * Registers a new user.
     *
     * @param user an ApplicationUser representing this new user
     */
    @PostMapping("api/auth/register")
    fun register(@RequestBody user: ApplicationUser): Boolean =
            userService.register(user)
}