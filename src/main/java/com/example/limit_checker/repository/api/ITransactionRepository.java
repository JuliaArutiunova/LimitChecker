package com.example.limit_checker.repository.api;

import com.example.limit_checker.repository.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
