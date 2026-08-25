package com.portfolio.finance_tracker.service;

import com.portfolio.finance_tracker.exception.CategoryNotFoundException;
import com.portfolio.finance_tracker.exception.TransactionNotFoundException;
import com.portfolio.finance_tracker.mapper.TransactionMapper;
import com.portfolio.finance_tracker.model.category.Category;
import com.portfolio.finance_tracker.model.transaction.CreateTransactionCommand;
import com.portfolio.finance_tracker.model.transaction.EditTransactionCommand;
import com.portfolio.finance_tracker.model.transaction.Transaction;
import com.portfolio.finance_tracker.model.transaction.TransactionDTO;
import com.portfolio.finance_tracker.model.transaction.TransactionListView;
import com.portfolio.finance_tracker.model.transaction.TransactionType;
import com.portfolio.finance_tracker.repository.CategoryRepository;
import com.portfolio.finance_tracker.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public TransactionDTO createTransaction(CreateTransactionCommand command) {

        Category category = categoryRepository.findById(command.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id: %d not found", command.getCategoryId())));

        Transaction transaction = transactionRepository.save(transactionMapper.fromCommand(command, category));


        return transactionMapper.toDto(transaction);
    }

    @Transactional
    public TransactionDTO updateTransaction(long id, EditTransactionCommand command) {
        Category category = categoryRepository.findById(command.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id: %d not found", command.getCategoryId())));

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(String.format("Transaction with id: %d not found", id)));

        transaction.setDescription(command.getDescription());
        transaction.setAmount(command.getAmount());
        transaction.setDate(command.getDate());
        transaction.setType(command.getType());
        transaction.setCategory(category);

        return transactionMapper.toDto(transaction);
    }

    public void delete(long id) {
        transactionRepository.deleteById(id);
    }

    public TransactionDTO findById(long id) {
        return transactionRepository.findById(id)
                .map(transactionMapper::toDto)
                .orElseThrow(() -> new TransactionNotFoundException(String.format("Transaction with id: %d not found!", id)));
    }

    public Page<TransactionListView> findAllBy(Pageable pageable) {
        return transactionRepository.findAllBy(pageable);
    }

    public Page<TransactionListView> findByCategoryId(long categoryId, Pageable pageable) {
        return transactionRepository.findByCategoryId(categoryId, pageable);
    }

    public Page<TransactionListView> findByDescription(String description, Pageable pageable) {
        return transactionRepository.findByDescription(description, pageable);
    }

    public Page<TransactionListView> findByAmountGreaterThan(BigDecimal amount, Pageable pageable) {
        return transactionRepository.findByAmountGreaterThan(amount, pageable);
    }

    public Page<TransactionListView> findByAmountLessThan(BigDecimal amount, Pageable pageable) {
        return transactionRepository.findByAmountLessThan(amount, pageable);
    }

    public Page<TransactionListView> findByAmountBetween(BigDecimal minAmount, BigDecimal maxAmount, Pageable pageable) {
        return transactionRepository.findByAmountBetween(minAmount, maxAmount, pageable);
    }

    public Page<TransactionListView> findByDateBetween(LocalDate from, LocalDate to, Pageable pageable) {
        return transactionRepository.findByDateBetween(from, to, pageable);
    }

    public Page<TransactionListView> findByType(TransactionType type, Pageable pageable) {
        return transactionRepository.findByType(type, pageable);
    }
}
