package com.exchange.transaction.repository;

import com.exchange.transaction.dto.Transaction;

import java.util.List;

public interface TransactionRepository {
    List<Transaction> getTransactions();
}
