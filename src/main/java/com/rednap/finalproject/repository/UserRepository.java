package com.rednap.finalproject.repository;

import com.rednap.finalproject.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <Long, UserEntity> {

}
