package com.portfolio.finance_tracker.repository;

import com.portfolio.finance_tracker.model.category.Category;
import com.portfolio.finance_tracker.model.category.CategoryType;
import com.portfolio.finance_tracker.model.transaction.Transaction;
import com.portfolio.finance_tracker.model.transaction.TransactionListView;
import com.portfolio.finance_tracker.model.transaction.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<TransactionListView> findAllBy(Pageable pageable);

    Page<TransactionListView> findByCategoryId(Long categoryId, Pageable pageable);

    Page<TransactionListView> findByDescription(String description, Pageable pageable);

    Page<TransactionListView> findByAmountGreaterThan(BigDecimal amount, Pageable pageable);

    Page<TransactionListView> findByAmountLessThan(BigDecimal amount, Pageable pageable);

    Page<TransactionListView> findByAmountBetween(BigDecimal minAmount, BigDecimal maxAmount, Pageable pageable);

    Page<TransactionListView> findByDateBetween(LocalDate from, LocalDate to, Pageable pageable);

    Page<TransactionListView> findByType(TransactionType type, Pageable pageable);

}
