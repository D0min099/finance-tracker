package com.portfolio.finance_tracker.repository;

import com.portfolio.finance_tracker.model.category.Category;
import com.portfolio.finance_tracker.model.category.CategoryType;
import com.portfolio.finance_tracker.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Set<Category> findByType(CategoryType type);
    Category findByTransaction(Transaction transaction);
}
