package com.core.walletservice.repositories;

import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exceptions.EntityNotFoundException;

public interface WalletRepository {

    Wallet createWallet(Wallet wallet) throws Exception;

    Wallet saveWallet(Wallet wallet) throws Exception;

    Wallet findByUsername(String username) throws EntityNotFoundException;

    Wallet findByUsernameAndUpdate(String username, double newBalance) throws EntityNotFoundException;

    Wallet save(Wallet wallet);
}
