package com.core.walletservice.dto;

public class WalletResponse {
    private String errorCode;
    private String message;
    private double balance;

    // Constructors
    public WalletResponse() {
    }

    public WalletResponse(String errorCode, String message, double balance) {
        this.errorCode = errorCode;
        this.message = message;
        this.balance = balance;
    }

    // Getters and Setters
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance(String walletId) {
        return 0.0;
    }
}
