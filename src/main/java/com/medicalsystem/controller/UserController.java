package com.medicalsystem.controller;

import com.medicalsystem.model.ApplicationUser;
import com.medicalsystem.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class UserController {

    private final ApplicationUserService applicationUserService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("api/auth/register")
    public ResponseEntity<String> register(@RequestBody ApplicationUser user) {

        // Check if no user with the given username
        ApplicationUser _user = applicationUserService.findByUsername(user.getUsername());
        if (_user != null) {
            // Log and return reponse
            String message = "User already exists: " + user.getUsername();
            log.info(message);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        // Encode user's password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Persist user
        applicationUserService.saveOrUpdate(user);

        // Log and return reponse
        String message = "User registered: " + user.getUsername();
        log.info(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
