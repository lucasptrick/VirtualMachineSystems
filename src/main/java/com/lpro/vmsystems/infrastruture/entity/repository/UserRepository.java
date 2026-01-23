package com.lpro.vmsystems.infrastruture.entity.repository;

import com.lpro.vmsystems.infrastruture.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}