package com.medicalsystem.service;

import com.medicalsystem.model.User;

public interface UserService extends CRUDService<User, Long> {
    boolean register(User user);
    boolean existsByUsername(String username);
    User findByUsername(String username);
}
