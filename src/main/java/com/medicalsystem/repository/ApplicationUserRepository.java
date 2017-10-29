package com.medicalsystem.repository;

import com.medicalsystem.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    ApplicationUser findByUsername(String username);

    boolean existsByUsername(String username);

}
