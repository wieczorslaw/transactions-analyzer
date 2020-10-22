package com.wieczorek.tanalyzer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionType {
    PAYMENT("PAYMENT"),
    REVERSAL("REVERSAL");

    private String name;
}
