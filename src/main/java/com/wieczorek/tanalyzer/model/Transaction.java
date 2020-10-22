package com.wieczorek.tanalyzer.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Transaction {
    private String id;
    private LocalDateTime date;
    private BigDecimal amount;
    private String merchant;
    private TransactionType type;
    private String relatedTransactionId;
}
