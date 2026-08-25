package com.portfolio.finance_tracker.model.transaction;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
public class CreateTransactionCommand {

    String description;
    BigDecimal amount;
    LocalDate date;
    TransactionType type;
    Long categoryId;
}
