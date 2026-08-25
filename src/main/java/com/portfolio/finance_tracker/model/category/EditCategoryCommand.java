package com.portfolio.finance_tracker.model.category;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class EditCategoryCommand {

    String name;
    CategoryType type;
    Set<Long> transactions;


}