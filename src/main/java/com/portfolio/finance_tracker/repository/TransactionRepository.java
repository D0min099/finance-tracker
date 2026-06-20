package com.portfolio.finance_tracker.repository;

import com.portfolio.finance_tracker.model.transaction.Transaction;
import com.portfolio.finance_tracker.model.category.CategoryType;
import com.portfolio.finance_tracker.model.transaction.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Set<Transaction> findByCategoryId(Long categoryId);
    Set<Transaction> findByDescription(String description);
    Set<Transaction> findByAmount(BigDecimal amount);
    Set<Transaction> findByDateBetween(LocalDate from, LocalDate to);
    Set<Transaction> findByType(TransactionType type);
    Set<Transaction> findByCategory(CategoryType category);
}
