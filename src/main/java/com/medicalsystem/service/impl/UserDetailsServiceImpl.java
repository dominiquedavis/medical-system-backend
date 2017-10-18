package com.medicalsystem.service.impl;

import com.medicalsystem.model.ApplicationUser;
import com.medicalsystem.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ApplicationUserService applicationUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = applicationUserService.findByUsername(username);

        if (user == null) {
            log.info("User not found: " + username);
            throw new UsernameNotFoundException(username);
        }

        return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }

}
