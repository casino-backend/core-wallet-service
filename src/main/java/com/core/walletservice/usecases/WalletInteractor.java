package com.core.walletservice.usecases;

import com.core.walletservice.dto.CreateWalletRequest;
import com.core.walletservice.dto.GetWalletRequest;
import com.core.walletservice.dto.UpdateWalletRequest;
import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exceptions.AppException;
import com.core.walletservice.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletInteractor implements WalletInputPort {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletInteractor(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet createWallet(CreateWalletRequest walletRequest) throws AppException {
        // Here you would contain your logic to create a wallet
        // using the walletRepository and then return the created Wallet entity
    }

    @Override
    public Wallet getBalance(GetWalletRequest walletRequest) throws AppException {
        return getWallet(walletRequest); // Assuming this is the desired behavior
    }

    @Override
    public Wallet getWallet(GetWalletRequest walletRequest) throws AppException {
        // Here you would contain your logic to get a wallet
        // using the walletRepository and then return the Wallet entity
    }

    @Override
    public Wallet updateWallet(UpdateWalletRequest walletRequest) throws AppException {
        // Here you would contain your logic to update a wallet
        // using the walletRepository and then return the updated Wallet entity
    }
}
