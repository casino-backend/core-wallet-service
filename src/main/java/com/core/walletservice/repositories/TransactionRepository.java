package com.core.walletservice.repositories;

import com.core.walletservice.entity.Transaction;
import com.core.walletservice.exceptions.EntityNotFoundException;

public interface TransactionRepository {

    Transaction createTransaction(Transaction transaction) throws Exception;

    Transaction findRecentTransaction(String username, String action, double amount) throws EntityNotFoundException;

    void save(Transaction transaction);
}
