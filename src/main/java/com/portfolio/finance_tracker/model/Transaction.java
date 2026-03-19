package com.portfolio.finance_tracker.model;

import com.portfolio.finance_tracker.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigInteger amount;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;


}
