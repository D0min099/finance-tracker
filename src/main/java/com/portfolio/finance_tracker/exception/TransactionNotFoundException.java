package com.portfolio.finance_tracker.exception;

import lombok.Builder;


public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
