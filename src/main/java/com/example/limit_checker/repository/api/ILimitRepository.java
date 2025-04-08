package com.example.limit_checker.repository.api;

import com.example.limit_checker.repository.entity.LimitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ILimitRepository extends JpaRepository<LimitEntity, UUID> {
}
