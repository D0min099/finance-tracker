package com.portfolio.finance_tracker.service;

import com.portfolio.finance_tracker.exception.CategoryNotFoundException;
import com.portfolio.finance_tracker.mapper.CategoryMapper;
import com.portfolio.finance_tracker.model.category.Category;
import com.portfolio.finance_tracker.model.category.CategoryDTO;
import com.portfolio.finance_tracker.model.category.CategoryListView;
import com.portfolio.finance_tracker.model.category.CategoryType;
import com.portfolio.finance_tracker.model.category.CreateCategoryCommand;
import com.portfolio.finance_tracker.model.category.EditCategoryCommand;
import com.portfolio.finance_tracker.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryDTO createCategory(CreateCategoryCommand command) {
        Category category = categoryRepository.save(categoryMapper.fromCommand(command));
        return categoryMapper.toDto(category);
    }

    @Transactional
    public CategoryDTO updateCategory(long id, EditCategoryCommand command) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id: %d, not found", id)));

        category.setName(command.getName());
        category.setType(command.getType());

        return categoryMapper.toDto(category);
    }

    public void delete(long id) {
        categoryRepository.deleteById(id);
    }

    public Page<CategoryListView> findAllBy(Pageable pageable) {
        return categoryRepository.findAllBy(pageable);
    }

    public Page<CategoryListView> findByType(CategoryType type, Pageable pageable) {
        return categoryRepository.findByType(type, pageable);
    }

}
