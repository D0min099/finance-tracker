package com.portfolio.finance_tracker.mapper;

import com.portfolio.finance_tracker.exception.CategoryNotFoundException;
import com.portfolio.finance_tracker.exception.TransactionNotFoundException;
import com.portfolio.finance_tracker.model.category.Category;
import com.portfolio.finance_tracker.model.transaction.CreateTransactionCommand;
import com.portfolio.finance_tracker.model.transaction.EditTransactionCommand;
import com.portfolio.finance_tracker.model.transaction.Transaction;
import com.portfolio.finance_tracker.model.transaction.TransactionDTO;
import com.portfolio.finance_tracker.repository.CategoryRepository;
import com.portfolio.finance_tracker.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TransactionMapper {

    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    public TransactionDTO toDto(Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .type(transaction.getType())
                .categoryId(transaction.getCategory().getId())
                .build();
    }

    public Transaction fromCommand(CreateTransactionCommand command) {
        Category category = categoryRepository.findById(command.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id: %d not found", command.getCategoryId())));

        return Transaction.builder()
                .description(command.getDescription())
                .amount(command.getAmount())
                .date(command.getDate())
                .type(command.getType())
                .category(category)
                .build();
    }

    public Transaction edit(long id, EditTransactionCommand command) {
        return transactionRepository.findById(id)
                .map(transactionToEdit -> {
                    transactionToEdit.setDescription(command.getDescription());
                    transactionToEdit.setAmount(command.getAmount());
                    transactionToEdit.setDate(command.getDate());
                    transactionToEdit.setType(command.getType());
                    transactionToEdit.setCategory(categoryRepository.findById(command.getCategoryId())
                            .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id: %d not found", command.getCategoryId()))));
                    return transactionToEdit;

                }).orElseThrow(() -> new TransactionNotFoundException(String.format("Transaction with id: %d not found", id)));
    }
}
