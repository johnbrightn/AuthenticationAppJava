package com.jbntech.auth.AuthenticationApp2.repository;

import com.jbntech.auth.AuthenticationApp2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
