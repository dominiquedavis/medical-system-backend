package com.medicalsystem.service;

import com.medicalsystem.model.ApplicationUser;

public interface ApplicationUserService extends CRUDService<ApplicationUser, Long> {
    boolean register(ApplicationUser user);
    boolean existsByUsername(String username);
    ApplicationUser getByUsername(String username);
    boolean updateStatus(long userId, String status);
    boolean updateAdminRights(long userId, boolean admin);
    boolean delete(long userId);
}
