package com.rednap.finalproject.repository;

import com.rednap.finalproject.model.entity.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeRepository extends JpaRepository<CodeEntity, Long> {
    Optional<CodeEntity> findByOrderEntityId(final Long orderId);
}
