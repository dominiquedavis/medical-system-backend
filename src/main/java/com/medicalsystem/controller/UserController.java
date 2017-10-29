package com.medicalsystem.controller;

import com.medicalsystem.model.User;
import com.medicalsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @PostMapping("api/auth/register")
    public boolean register(@RequestBody User user) {
        return userService.register(user);
    }
}
