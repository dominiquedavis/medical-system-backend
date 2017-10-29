package com.medicalsystem.controller;

import com.medicalsystem.model.ApplicationUser;
import com.medicalsystem.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final ApplicationUserService userService;

    @GetMapping("api/users")
    public List<ApplicationUser> getUsers() {
        return userService.getAll();
    }

    @GetMapping("api/users/statusList")
    public List<String> getStatusList() {
        // TODO
        return Arrays.asList("accepted", "waiting");
    }

    @PostMapping("api/users/{userId}/status")
    public boolean updateStatus(@PathVariable long userId, @RequestBody String status) {
        return userService.updateStatus(userId, status);
    }

    @PostMapping("api/users/{userId}/admin")
    public boolean updateAdminRights(@PathVariable long userId, @RequestBody boolean admin) {
        return userService.updateAdminRights(userId, admin);
    }

    @DeleteMapping("api/users/{userId}")
    public boolean deleteUser(@PathVariable long userId) {
        return userService.delete(userId);
    }

    @PostMapping("api/auth/register")
    public boolean register(@RequestBody ApplicationUser user) {
        return userService.register(user);
    }
}
