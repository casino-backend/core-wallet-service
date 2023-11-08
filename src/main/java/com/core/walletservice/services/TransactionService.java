package com.core.walletservice.services;

import com.core.walletservice.dto.TransactionRequest;
import com.core.walletservice.dto.TransactionResponse;
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
        // Validate the deposit amount
        if (transactionRequest.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid amount for deposit.");
        }

        // Update wallet balance
        Wallet wallet = walletService.updateWalletBalance(
                transactionRequest.getUsername(),
                walletService.getBalance(transactionRequest).getBalance() + transactionRequest.getAmount()
        );

        // Record the transaction
        Transaction transaction = new Transaction(
                wallet.getUsername(),
                TransactionType.DEPOSIT,
                transactionRequest.getAmount(),
                wallet.getBalance() - transactionRequest.getAmount(), // before balance
                wallet.getBalance(), // after balance
                LocalDateTime.now()
        );
        transactionRepository.save(transaction);

        // Create and return the response
        return new TransactionResponse(wallet.getUsername(), transactionRequest.getAmount(), wallet.getBalance() - transactionRequest.getAmount(), wallet.getBalance(), transaction.getId());
    }

    @Transactional
    public TransactionResponse withdraw(TransactionRequest transactionRequest) throws EntityNotFoundException, InsufficientFundsException {
        // Validate withdrawal amount
        if (transactionRequest.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid amount for withdrawal.");
        }

        Wallet wallet = walletService.getBalance(transactionRequest).toWallet(); // Assuming we have a method to convert WalletResponse to Wallet entity

        // Check for sufficient funds
        if (wallet.getBalance() < transactionRequest.getAmount()) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }

        // Update wallet balance
        wallet = walletService.updateWalletBalance(
                transactionRequest.getUsername(),
                wallet.getBalance() - transactionRequest.getAmount()
        );

        // Record the transaction
        Transaction transaction = new Transaction(
                wallet.getUsername(),
                TransactionType.WITHDRAW,
                transactionRequest.getAmount(),
                wallet.getBalance() + transactionRequest.getAmount(), // before balance
                wallet.getBalance(), // after balance
                LocalDateTime.now()
        );
        transactionRepository.save(transaction);

        // Create and return the response
        return new TransactionResponse(wallet.getUsername(), transactionRequest.getAmount(), wallet.getBalance() + transactionRequest.getAmount(), wallet.getBalance(), transaction.getId());
    }
}
