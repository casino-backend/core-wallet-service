package com.core.walletservice.dto;

public class GetWalletRequest {
    private String username;

    // Constructor
    public GetWalletRequest(String username) {
        this.username = username;
    }

    // Getter and Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

// Existing CreateWalletRequest, CreateWalletResponse, and UpdateWalletRequest classes remain unchanged

