package com.portfolio.finance_tracker.model.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TransactionListView {

    long getIf();
    String shortName();
    BigDecimal amount();
    LocalDate date();

}
