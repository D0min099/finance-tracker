package com.portfolio.finance_tracker.model.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {

    Long id;
    String name;
    CategoryType type;
    Set<Long> transactionsIds;

}
