package com.core.walletservice.services.impl;

import com.core.walletservice.dto.GetWalletRequest;
import com.core.walletservice.dto.TransactionRequest;
import com.core.walletservice.dto.TransactionResponse;
import com.core.walletservice.entity.Transaction;
import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exceptions.EntityNotFoundException;
import com.core.walletservice.exceptions.InsufficientFundsException;
import com.core.walletservice.repositories.TransactionRepository;
import com.core.walletservice.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final WalletServiceImpl walletServiceImpl;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(WalletServiceImpl walletServiceImpl, TransactionRepository transactionRepository) {
        this.walletServiceImpl = walletServiceImpl;
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public TransactionResponse deposit(TransactionRequest transactionRequest) throws EntityNotFoundException {
        if (transactionRequest.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid amount for deposit.");
        }

        // Create GetWalletRequest from TransactionRequest
        GetWalletRequest getWalletRequest = new GetWalletRequest();
        getWalletRequest.setUsername(transactionRequest.getUsername());

        Wallet wallet = walletServiceImpl.updateWalletBalance(
                transactionRequest.getUsername(),
                walletServiceImpl.getBalance(getWalletRequest).getBalance() + transactionRequest.getAmount()
        );

        Transaction transaction = new Transaction();
        transaction.setUsername(wallet.getUsername());
        transaction.setAction("DEPOSIT");
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setBeforeBalance(wallet.getBalance() - transactionRequest.getAmount());
        transaction.setAfterBalance(wallet.getBalance());
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);

        return new TransactionResponse(wallet.getUsername(), transactionRequest.getAmount(), wallet.getBalance() - transactionRequest.getAmount(), wallet.getBalance(), transaction.getId());
    }

    @Override
    @Transactional
    public TransactionResponse withdraw(TransactionRequest transactionRequest) throws EntityNotFoundException, InsufficientFundsException {
        if (transactionRequest.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid amount for withdrawal.");
        }

        // Create GetWalletRequest from TransactionRequest
        GetWalletRequest getWalletRequest = new GetWalletRequest();
        getWalletRequest.setUsername(transactionRequest.getUsername());

        Wallet wallet = walletServiceImpl.getBalance(getWalletRequest).toWallet();

        if (wallet.getBalance() < transactionRequest.getAmount()) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }

        wallet = walletServiceImpl.updateWalletBalance(
                transactionRequest.getUsername(),
                wallet.getBalance() - transactionRequest.getAmount()
        );

        Transaction transaction = new Transaction();
        transaction.setUsername(wallet.getUsername());
        transaction.setAction("WITHDRAW");
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setBeforeBalance(wallet.getBalance() + transactionRequest.getAmount());
        transaction.setAfterBalance(wallet.getBalance());
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);

        return new TransactionResponse(wallet.getUsername(), transactionRequest.getAmount(), wallet.getBalance() + transactionRequest.getAmount(), wallet.getBalance(), transaction.getId());
    }
}
