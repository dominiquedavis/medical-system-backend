package com.medicalsystem.service;

import com.medicalsystem.model.ApplicationUser;

public interface ApplicationUserService extends CRUDService<ApplicationUser, Long> {
    boolean register(ApplicationUser user);
    boolean existsByUsername(String username);
    ApplicationUser findByUsername(String username);
}
