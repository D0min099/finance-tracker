package com.portfolio.finance_tracker.model.category;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Builder
@Value
public class CreateCategoryCommand {

    String name;
    CategoryType categoryType;
    Set<Long> transactionsId;
}
