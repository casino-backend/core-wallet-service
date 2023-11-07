package com.core.walletservice.usecases;

import com.core.walletservice.dto.CreateWalletRequest;
import com.core.walletservice.dto.GetWalletRequest;
import com.core.walletservice.dto.UpdateWalletRequest;
import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exceptions.AppException;

public interface WalletInputPort {
    Wallet createWallet(CreateWalletRequest walletRequest) throws AppException;
    Wallet getBalance(GetWalletRequest walletRequest) throws AppException;
    Wallet getWallet(GetWalletRequest walletRequest) throws AppException;
    Wallet updateWallet(UpdateWalletRequest walletRequest) throws AppException;
}
