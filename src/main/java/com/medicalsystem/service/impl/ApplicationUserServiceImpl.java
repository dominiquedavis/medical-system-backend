package com.medicalsystem.service.impl;

import com.medicalsystem.model.ApplicationUser;
import com.medicalsystem.repository.ApplicationUserRepository;
import com.medicalsystem.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean register(ApplicationUser user) {
        if (existsByUsername(user.getUsername())) {
            log.info("User exists with a given username: " + user.getUsername());
            return false;
        }

        encryptPassword(user);
        save(user);

        log.info("User registered: " + user.getUsername());
        return true;
    }

    @Override
    public boolean existsByUsername(String username) {
        return applicationUserRepository.existsByUsername(username);
    }

    @Override
    public ApplicationUser getByUsername(String username) {
        return applicationUserRepository.findByUsername(username);
    }

    @Override
    public boolean updateStatus(long userId, String status) {
        ApplicationUser user = getById(userId);

        if (user == null) {
            log.info("User not found with ID: " + userId);
            return false;
        }

        user.setStatus(status);
        save(user);

        log.info(String.format("Status updated for user '%d': '%s'", userId, status));
        return true;
    }

    @Override
    public boolean updateAdminRights(long userId, boolean admin) {
        ApplicationUser user = getById(userId);

        if (user == null) {
            log.info("User not found with ID: " + userId);
            return false;
        }

        user.setAdmin(admin);
        save(user);

        log.info(String.format("Admin rights set to '%b' for user: '%d'", admin, userId));
        return true;
    }

    @Override
    public boolean delete(long userId) {
        if (existsById(userId)) {
            log.info("User not found with ID: " + userId);
            return false;
        }
        deleteById(userId);
        return true;
    }

    @Override
    public List<ApplicationUser> getAll() {
        return applicationUserRepository.findAll();
    }

    @Override
    public ApplicationUser getById(Long id) {
        return applicationUserRepository.findOne(id);
    }

    @Override
    public boolean existsById(Long id) {
        return applicationUserRepository.exists(id);
    }

    @Override
    public ApplicationUser save(ApplicationUser user) {
        return applicationUserRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        applicationUserRepository.delete(id);
    }

    private void encryptPassword(ApplicationUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
