package com.medicalsystem.service.impl;

import com.medicalsystem.model.ApplicationUser;
import com.medicalsystem.repository.ApplicationUserRepository;
import com.medicalsystem.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<ApplicationUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public ApplicationUser findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public ApplicationUser saveOrUpdate(ApplicationUser user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(ApplicationUser user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.delete(id);
    }

    @Override
    public ApplicationUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean register(ApplicationUser user) {
        if (findByUsername(user.getUsername()) != null) {
            return false;
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Persist user
        saveOrUpdate(user);

        return true;
    }

}
