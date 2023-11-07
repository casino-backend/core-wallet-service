package com.core.walletservice.controllers;

import com.core.walletservice.dto.TransactionRequest;
import com.core.walletservice.exceptions.AppException;
import com.core.walletservice.responses.StandardResponse;
import com.core.walletservice.usecases.TransactionInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionInputPort transactionInteractor;

    @Autowired
    public TransactionController(TransactionInputPort transactionInteractor) {
        this.transactionInteractor = transactionInteractor;
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody TransactionRequest transactionRequest) {
        try {
            Object response = transactionInteractor.deposit(transactionRequest);
            return ResponseEntity.ok(new StandardResponse(response, "Success"));
        } catch (AppException e) {
            // Custom exception handling
            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new StandardResponse(null, e.getMessage()));
        } catch (Exception e) {
            // Generic exception handling
            return ResponseEntity
                    .internalServerError()
                    .body(new StandardResponse(null, "An unexpected error occurred"));
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody TransactionRequest transactionRequest) {
        try {
            Object response = transactionInteractor.withdraw(transactionRequest);
            return ResponseEntity.ok(new StandardResponse(response, "Success"));
        } catch (AppException e) {
            // Custom exception handling
            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new StandardResponse(null, e.getMessage()));
        } catch (Exception e) {
            // Generic exception handling
            return ResponseEntity
                    .internalServerError()
                    .body(new StandardResponse(null, "An unexpected error occurred"));
        }
    }
}
