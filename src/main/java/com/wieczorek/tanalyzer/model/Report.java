package com.wieczorek.tanalyzer.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Report {
    private int numberOfTransactions;
    private BigDecimal averageAmount;
}
