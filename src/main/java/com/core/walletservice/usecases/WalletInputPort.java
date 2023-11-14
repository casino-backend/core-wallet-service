package com.core.walletservice.usecases;

import com.core.walletservice.dto.CreateWalletRequest;
import com.core.walletservice.dto.GetWalletRequest;
import com.core.walletservice.dto.UpdateWalletRequest;
import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exceptions.EntityNotFoundException;
import com.core.walletservice.exceptions.NotFoundException;


public interface WalletInputPort {
    Wallet createWallet(CreateWalletRequest walletRequest) throws Exception;

    double getBalance(GetWalletRequest walletRequest) throws EntityNotFoundException, NotFoundException;

    Wallet getWallet(GetWalletRequest walletRequest) throws EntityNotFoundException, NotFoundException;

    Wallet updateWallet(UpdateWalletRequest walletRequest) throws EntityNotFoundException, NotFoundException;
}
