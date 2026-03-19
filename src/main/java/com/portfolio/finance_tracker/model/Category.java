package com.portfolio.finance_tracker.model;

import com.portfolio.finance_tracker.model.enums.CategoryType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryType type;

    @OneToMany(mappedBy = "category")
    private Set<Transaction> transaction;

}
