package com.medicalsystem.service.impl;

import com.medicalsystem.model.ApplicationUser;
import com.medicalsystem.repository.ApplicationUserRepository;
import com.medicalsystem.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            log.info("ApplicationUser exists with a given username: " + user.getUsername());
            return false;
        }

        encryptPassword(user);
        save(user);

        log.info("ApplicationUser registered: " + user.getUsername());
        return true;
    }

    @Override
    public boolean existsByUsername(String username) {
        return applicationUserRepository.existsByUsername(username);
    }

    @Override
    public ApplicationUser findByUsername(String username) {
        return applicationUserRepository.findByUsername(username);
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
