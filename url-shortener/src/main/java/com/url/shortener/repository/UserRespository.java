package com.url.shortener.repository;

import com.url.shortener.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRespository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsUsersByEmail(String email);

    boolean existsUsersByUsername(String username);
}
