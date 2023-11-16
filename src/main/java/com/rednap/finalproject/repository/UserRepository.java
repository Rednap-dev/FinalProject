package com.rednap.finalproject.repository;

import com.rednap.finalproject.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Long> {
    Optional<UserEntity> findByUsername(final String username);
    boolean existsByUsernameOrEmail(final String username, final String email);
}
