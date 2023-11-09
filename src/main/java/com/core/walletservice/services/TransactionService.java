package com.core.walletservice.services;

import com.core.walletservice.dto.TransactionRequest;
import com.core.walletservice.dto.TransactionResponse;
import com.core.walletservice.dto.GetWalletRequest; // Importing GetWalletRequest
import com.core.walletservice.entity.Transaction;
import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exceptions.EntityNotFoundException;
import com.core.walletservice.exceptions.InsufficientFundsException;
import com.core.walletservice.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final WalletService walletService;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(WalletService walletService, TransactionRepository transactionRepository) {
        this.walletService = walletService;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public TransactionResponse deposit(TransactionRequest transactionRequest) throws EntityNotFoundException {
        if (transactionRequest.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid amount for deposit.");
        }

        // Create GetWalletRequest from TransactionRequest
        GetWalletRequest getWalletRequest = new GetWalletRequest();
        getWalletRequest.setUsername(transactionRequest.getUsername());

        Wallet wallet = walletService.updateWalletBalance(
                transactionRequest.getUsername(),
                walletService.getBalance(getWalletRequest).getBalance() + transactionRequest.getAmount()
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

    @Transactional
    public TransactionResponse withdraw(TransactionRequest transactionRequest) throws EntityNotFoundException, InsufficientFundsException {
        if (transactionRequest.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid amount for withdrawal.");
        }

        // Create GetWalletRequest from TransactionRequest
        GetWalletRequest getWalletRequest = new GetWalletRequest();
        getWalletRequest.setUsername(transactionRequest.getUsername());

        Wallet wallet = walletService.getBalance(getWalletRequest).toWallet();

        if (wallet.getBalance() < transactionRequest.getAmount()) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }

        wallet = walletService.updateWalletBalance(
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
