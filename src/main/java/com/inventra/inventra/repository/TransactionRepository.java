package com.inventra.inventra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inventra.inventra.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
