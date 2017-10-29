package com.medicalsystem.controller;

import com.medicalsystem.model.ApplicationUser;
import com.medicalsystem.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final ApplicationUserService userService;

    @PostMapping("api/auth/register")
    public boolean register(@RequestBody ApplicationUser user) {
        return userService.register(user);
    }
}
