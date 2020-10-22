package com.wieczorek.tanalyzer.service;

import com.wieczorek.tanalyzer.model.Report;
import com.wieczorek.tanalyzer.model.Transaction;
import com.wieczorek.tanalyzer.service.util.DateUtil;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;

public class ReportService {
    public static Report analyze(File file, String fromDate, String toDate, String merchant) {
        HashSet<Transaction> transactions = TransactionsReadService.getTransactionsForMerchantWithRange(file, DateUtil.toDate(fromDate), DateUtil.toDate(toDate), merchant);
        System.out.println("Number of transactions = " + transactions.size());
        BigDecimal sum = transactions.stream().map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal average = sum.divide(new BigDecimal(transactions.size()), RoundingMode.UNNECESSARY);
        System.out.println("Average transaction value = " + average.toString());
        return new Report(transactions.size(), average);
    }
}
