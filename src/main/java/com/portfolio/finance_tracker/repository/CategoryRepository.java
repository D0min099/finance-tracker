package com.portfolio.finance_tracker.repository;

import com.portfolio.finance_tracker.model.Category;
import com.portfolio.finance_tracker.model.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Set<Category> findByType(CategoryType type);
}
