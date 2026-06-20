package com.portfolio.finance_tracker.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO {

    Long id;
    String description;
    BigDecimal amount;
    LocalDate date;
    TransactionType type;
    Long categoryId;
}
