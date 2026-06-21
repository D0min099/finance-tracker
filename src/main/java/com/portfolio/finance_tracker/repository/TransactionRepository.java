package com.portfolio.finance_tracker.repository;

import com.portfolio.finance_tracker.model.category.CategoryType;
import com.portfolio.finance_tracker.model.transaction.Transaction;
import com.portfolio.finance_tracker.model.transaction.TransactionListView;
import com.portfolio.finance_tracker.model.transaction.TransactionType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<TransactionListView> findAllBy(Pageable pageable);

    Page<TransactionListView> findByCategoryId(Long categoryId, Pageable pageable);

    Set<Transaction> findByDescription(String description);

    Set<Transaction> findByAmount(BigDecimal amount);

    Set<Transaction> findByDateBetween(LocalDate from, LocalDate to);

    Set<Transaction> findByType(TransactionType type);

    Set<Transaction> findByCategory(CategoryType category);
}
