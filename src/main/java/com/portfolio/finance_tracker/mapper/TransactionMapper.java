package com.portfolio.finance_tracker.mapper;

import com.portfolio.finance_tracker.model.category.Category;
import com.portfolio.finance_tracker.model.transaction.CreateTransactionCommand;
import com.portfolio.finance_tracker.model.transaction.Transaction;
import com.portfolio.finance_tracker.model.transaction.TransactionDTO;
import com.portfolio.finance_tracker.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionMapper {

    private final CategoryRepository categoryRepository;


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

    public Transaction fromCommand(CreateTransactionCommand command, Category category) {
        return Transaction.builder()
                .description(command.getDescription())
                .amount(command.getAmount())
                .date(command.getDate())
                .type(command.getType())
                .category(category)
                .build();
    }

}
