package com.core.walletservice.usecases;

import com.core.walletservice.dto.CreateWalletRequest;
import com.core.walletservice.dto.GetWalletRequest;
import com.core.walletservice.dto.UpdateWalletRequest;
import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exceptions.EntityNotFoundException;
import com.core.walletservice.exceptions.NotFoundException;
import com.core.walletservice.repositories.WalletRepository;

public class WalletInteractor implements WalletInputPort {

    private final WalletRepository walletRepo;

    public WalletInteractor(WalletRepository walletRepo) {
        this.walletRepo = walletRepo;
    }

    @Override
    public Wallet createWallet(CreateWalletRequest walletRequest) throws Exception {
        Wallet newWallet = new Wallet();
        newWallet.setToken(walletRequest.getToken());
        newWallet.setUsername(walletRequest.getUsername());
        newWallet.setBalance(walletRequest.getBalance());
        newWallet.setType(walletRequest.getType()); // Ensure that the UserType is correctly handled in Java
        newWallet.setUpline(walletRequest.getUpline());
        newWallet.setRefSale(walletRequest.getRefSale());

        Wallet savedWallet = walletRepo.save(newWallet);
        if (savedWallet == null) {
            throw new NotFoundException("Failed to create wallet");
        }
        return savedWallet;
    }

    @Override
    public double getBalance(GetWalletRequest walletRequest) throws EntityNotFoundException, NotFoundException {
        Wallet foundWallet = walletRepo.findByUsername(walletRequest.getUsername());
        if (foundWallet == null) {
            throw new NotFoundException("Wallet not found");
        }
        return foundWallet.getBalance();
    }

    @Override
    public Wallet getWallet(GetWalletRequest walletRequest) throws EntityNotFoundException, NotFoundException {
        Wallet foundWallet = walletRepo.findByUsername(walletRequest.getUsername());
        if (foundWallet == null) {
            throw new NotFoundException("Wallet not found");
        }
        return foundWallet;
    }

    @Override
    public Wallet updateWallet(UpdateWalletRequest walletRequest) throws EntityNotFoundException, NotFoundException {


        Wallet updatedWallet = walletRepo.updateBalanceByUsername(
                walletRequest.getUsername(),
                walletRequest.getAmountAfter()
        );
        if (updatedWallet == null) {
            throw new NotFoundException("Wallet not found");
        }
        return updatedWallet;
    }
}
