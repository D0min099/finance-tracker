package com.portfolio.finance_tracker.service;

import com.portfolio.finance_tracker.mapper.CategoryMapper;
import com.portfolio.finance_tracker.model.category.Category;
import com.portfolio.finance_tracker.model.category.CategoryDTO;
import com.portfolio.finance_tracker.model.category.CategoryListView;
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
        Category category = categoryMapper.fromCommand(command);
        return categoryMapper.toDto(category);
    }

    @Transactional
    public Page<CategoryListView> findAllBy(Pageable pageable){
        return categoryRepository.findAllBy(pageable);
    }

    @Transactional
    public CategoryDTO updateCategory(long id, EditCategoryCommand command){
        Category update = categoryMapper.edit(id, command);
        return categoryMapper.toDto(update);
    }

    public void delete(long id){
        categoryRepository.deleteById(id);
    }


}
