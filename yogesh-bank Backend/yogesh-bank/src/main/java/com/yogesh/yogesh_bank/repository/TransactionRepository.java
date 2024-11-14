package com.yogesh.yogesh_bank.repository;

import com.yogesh.yogesh_bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String > {
    Optional<Transaction> findByTransactionId(String transactionId);
}
