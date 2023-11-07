package com.core.walletservice.services;

import com.core.walletservice.dto.GetWalletRequest;
import com.core.walletservice.dto.WalletResponse;
import com.core.walletservice.entity.Wallet;
import com.core.walletservice.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Wallet updateWalletBalance(String username, double newBalance) {
        Wallet wallet = walletRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Wallet not found for username: " + username));

        wallet.setBalance(newBalance);
        return walletRepository.save(wallet);
    }

    // other service methods...
}

