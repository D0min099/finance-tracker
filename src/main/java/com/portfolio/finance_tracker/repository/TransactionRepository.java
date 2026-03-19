package com.portfolio.finance_tracker.repository;

import com.portfolio.finance_tracker.model.Transaction;
import com.portfolio.finance_tracker.model.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Set;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Set<Transaction> findByType(TransactionType type);
    Set<Transaction> findByDateBetween(LocalDate from, LocalDate to);
    Set<Transaction> findByCategoryId(Long categoryId);
}
