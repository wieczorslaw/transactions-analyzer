package com.wieczorek.tanalyzer.service;

import com.wieczorek.tanalyzer.model.Transaction;
import com.wieczorek.tanalyzer.model.TransactionType;
import com.wieczorek.tanalyzer.service.util.DateUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TransactionsReadService {

    private static final int ID_COL = 0;
    private static final int DATE_COL = 1;
    private static final int AMOUNT_COL = 2;
    private static final int MERCHANT_COL = 3;
    private static final int TYPE_COL = 4;
    private static final int RELATED_TRANSACTION_COL = 5;

    public static Set<Transaction> getTransactionsForMerchantWithRange(File file, LocalDateTime fromDate, LocalDateTime toDate, String merchant) {
        Map<String, Transaction> map = new HashMap<>();
        //file can be huge, read line by line
        try (FileReader fileReader = new FileReader(file); BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            boolean isFirstRow = true;
            while ((line = br.readLine()) != null) {
                if (isFirstRow) {
                    //first column with names - omit
                    isFirstRow = false;
                    continue;
                }
                String[] row = line.split(",");
                Transaction transaction = createTransaction(row);
                if (!transaction.getMerchant().equalsIgnoreCase(merchant)) {
                    //wrong merchant
                    continue;
                }
                if (transaction.getType() == TransactionType.PAYMENT && DateUtil.isBetween(transaction.getDate(), fromDate, toDate)) {
                    map.put(transaction.getId(), transaction);
                }
                if (transaction.getType() == TransactionType.REVERSAL) {
                    map.remove(transaction.getRelatedTransactionId());
                }
            }
            return new HashSet<>(map.values());
        } catch (IOException e) {
            throw new RuntimeException("Error on reading csv file", e);
        }
    }

    private static Transaction createTransaction(String[] row) {
        Transaction t = new Transaction();
        t.setId(row[ID_COL]);
        t.setDate(DateUtil.toDate(row[DATE_COL]));
        t.setAmount(new BigDecimal(row[AMOUNT_COL]));
        t.setMerchant(row[MERCHANT_COL]);
        TransactionType type = TransactionType.valueOf(row[TYPE_COL]);
        t.setType(type);
        if (t.getType() == TransactionType.REVERSAL) {
            t.setRelatedTransactionId(row[RELATED_TRANSACTION_COL]);
        }
        return t;
    }
}
