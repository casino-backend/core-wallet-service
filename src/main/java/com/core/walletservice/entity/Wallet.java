package com.core.walletservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "wallets")
public class Wallet {

    @Id
    private String id; // MongoDB uses String type for ObjectID

    @Field("token")
    private String token;

    @Field("username")
    private String username;

    @Field("balance")
    private double balance; // Using double for floating point numbers

    @Field("type")
    private UserType type;

    @Field("refSale")
    private String refSale;

    @Field("upline")
    private String upline;

    @Field("createdAt")
    private LocalDateTime createdAt;

    @Field("updatedAt")
    private LocalDateTime updatedAt;

    // Constructors, getters, setters, and other methods omitted for brevity
}

enum UserType {
    // Assuming you have predefined user types, they will be defined here
    ADMIN, USER, GUEST;
}
