package com.ck.practice.SpringSecurityAuthJwt.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ck.practice.SpringSecurityAuthJwt.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
