package com.medicalsystem.controller;

import com.medicalsystem.model.ApplicationUser;
import com.medicalsystem.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class UserController {

    private final ApplicationUserService applicationUserService;

    @PostMapping("api/auth/register")
    public ResponseEntity<String> register(@RequestBody ApplicationUser user) {
        if (applicationUserService.register(user)) {
            return new ResponseEntity<>("User registered: " + user.getUsername(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User already exists: " + user.getUsername(), HttpStatus.BAD_REQUEST);
        }
    }


}
