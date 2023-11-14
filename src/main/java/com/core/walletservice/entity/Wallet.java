package com.core.walletservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "wallet")
public class Wallet {

    private final UUID id; // Assuming the ID is of type UUID for database identity
    private String username;
    private double balance;
    private String type; // e.g., "personal", "business"
    private String upline; // Reference to the entity that introduced the user
    private String refSale; // Reference to the sales entity or code

    // Add other fields that are relevant to your application

    // Constructors
    public Wallet() {
        this.id = UUID.randomUUID(); // Automatically generate ID
    }

    public Wallet(String username, double balance, String type, String upline, String refSale) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.balance = balance;
        this.type = type;
        this.upline = upline;
        this.refSale = refSale;
    }

    // Getters and setters for all fields

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpline() {
        return upline;
    }

    public void setUpline(String upline) {
        this.upline = upline;
    }

    public String getRefSale() {
        return refSale;
    }

    public void setRefSale(String refSale) {
        this.refSale = refSale;
    }

    // Other methods, such as for depositing or withdrawing money, may also be included

    // Example toString() method
    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", balance=" + balance +
                ", type='" + type + '\'' +
                ", upline='" + upline + '\'' +
                ", refSale='" + refSale + '\'' +
                // Include other fields in the string representation
                '}';
    }

    public void setToken(String token) {
    }

    // hashCode, equals, and other utility methods if needed
}
