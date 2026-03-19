package com.portfolio.finance_tracker.repository;

import com.portfolio.finance_tracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
