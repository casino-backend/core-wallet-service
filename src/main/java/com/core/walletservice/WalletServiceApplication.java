package com.core.walletservice;

import com.core.walletservice.controllers.TransactionController;
import com.core.walletservice.controllers.WalletController;
import com.core.walletservice.services.TransactionService;
import com.core.walletservice.services.WalletService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
// other imports...

@SpringBootApplication
public class WalletServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletServiceApplication.class, args);
	}

	@Bean
	public WalletController walletController(WalletService walletService) {
		return new WalletController(walletService);
	}

	@Bean
	public TransactionController transactionController(TransactionService transactionService) {
		TransactionService TransactionService = null;
		return new TransactionController(TransactionService);
	}

}
