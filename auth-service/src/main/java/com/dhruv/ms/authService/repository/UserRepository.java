package com.dhruv.ms.authService.repository;

import com.dhruv.ms.authService.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserCredential,Long> {
    Optional<UserCredential> findByUsername(String username);

}
