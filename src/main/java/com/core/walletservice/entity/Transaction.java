package com.core.walletservice.entity;

import java.time.LocalDateTime;

public class Transaction {

    private String id;
    private String username;
    private String action;
    private double amount;
    private double beforeBalance;
    private double afterBalance;
    private LocalDateTime timestamp;

    // Default constructor
    public Transaction() {
    }

    // Constructor with all fields
    public Transaction(String username, String action, double amount, double beforeBalance, double afterBalance, LocalDateTime timestamp) {
        this.username = username;
        this.action = action;
        this.amount = amount;
        this.beforeBalance = beforeBalance;
        this.afterBalance = afterBalance;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBeforeBalance() {
        return beforeBalance;
    }

    public void setBeforeBalance(double beforeBalance) {
        this.beforeBalance = beforeBalance;
    }

    public double getAfterBalance() {
        return afterBalance;
    }

    public void setAfterBalance(double afterBalance) {
        this.afterBalance = afterBalance;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // Additional methods as required...
}
