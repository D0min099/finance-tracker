package com.portfolio.finance_tracker.service;

import com.portfolio.finance_tracker.exception.TransactionNotFoundException;
import com.portfolio.finance_tracker.mapper.TransactionMapper;
import com.portfolio.finance_tracker.model.transaction.CreateTransactionCommand;
import com.portfolio.finance_tracker.model.transaction.EditTransactionCommand;
import com.portfolio.finance_tracker.model.transaction.Transaction;
import com.portfolio.finance_tracker.model.transaction.TransactionDTO;
import com.portfolio.finance_tracker.model.transaction.TransactionListView;
import com.portfolio.finance_tracker.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;

    public TransactionDTO createTransaction(CreateTransactionCommand command) {
        Transaction transaction = transactionMapper.fromCommand(command);
        return transactionMapper.toDto(transaction);
    }

    public Page<TransactionListView> findAllBy(Pageable pageable) {
        return transactionRepository.findAllBy(pageable);
    }

    public TransactionDTO findById(long id) {
        return transactionRepository.findById(id)
                .map(transactionMapper::toDto)
                .orElseThrow(() -> new TransactionNotFoundException(String.valueOf("Transaction with id: %d not found!")));
    }

    public TransactionDTO updateTransaction(long id, EditTransactionCommand command) {
        Transaction update = transactionMapper.edit(id, command);
        return transactionMapper.toDto(update);
    }

    public void delete(long id) {
        transactionRepository.deleteById(id);
    }

    public Page<TransactionListView> findByCategoryId(long categoryId, Pageable pageable) {
        return transactionRepository.findByCategoryId(categoryId, pageable);
    }
}
