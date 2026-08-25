package com.portfolio.finance_tracker.mapper;

import com.portfolio.finance_tracker.exception.CategoryNotFoundException;
import com.portfolio.finance_tracker.model.category.Category;
import com.portfolio.finance_tracker.model.category.CategoryDTO;
import com.portfolio.finance_tracker.model.category.CreateCategoryCommand;
import com.portfolio.finance_tracker.model.category.EditCategoryCommand;
import com.portfolio.finance_tracker.repository.CategoryRepository;
import com.portfolio.finance_tracker.repository.TransactionRepository;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    CategoryRepository categoryRepository;
    TransactionRepository transactionRepository;


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

    public Category edit(long id, EditCategoryCommand command){
        return categoryRepository.findById(id)
                .map(categoryToEdit -> {
                            categoryToEdit.setName(command.getName());
                            categoryToEdit.setType(command.getType());
                            return categoryToEdit;
                        }).orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id: %d not found", id)));
    }

}
