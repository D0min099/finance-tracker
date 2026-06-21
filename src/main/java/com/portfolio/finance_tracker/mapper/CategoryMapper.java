package com.portfolio.finance_tracker.mapper;

import com.portfolio.finance_tracker.model.category.Category;
import com.portfolio.finance_tracker.model.category.CategoryDTO;
import com.portfolio.finance_tracker.model.category.CreateCategoryCommand;
import com.portfolio.finance_tracker.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    CategoryRepository categoryRepository;

    public CategoryDTO toDto(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .type(category.getType())
                .build();
    }

    public Category fromCommand(CreateCategoryCommand command) {
        return Category.builder()
                .name(command.getName())
                .type(command.getCategoryType())
                .build();
    }


}
