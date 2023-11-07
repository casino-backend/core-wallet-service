package com.core.walletservice.repositories;

import com.core.walletservice.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Assuming that there is a need to find the most recent transaction based on a username, action, and amount.
    // The following method would need to be implemented using a custom JPQL query or using method query derivation.
    Optional<Transaction> findTopByUsernameAndActionAndAmountOrderByCreatedAtDesc(
            String username,
            String action,
            double amount
    );

    // You can define other custom query methods here if needed.
}
