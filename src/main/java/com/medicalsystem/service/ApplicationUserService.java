package com.medicalsystem.service;

import com.medicalsystem.model.ApplicationUser;

public interface ApplicationUserService extends CRUDService<ApplicationUser, Long> {

    ApplicationUser findByUsername(String username);

    boolean register(ApplicationUser user);

}
