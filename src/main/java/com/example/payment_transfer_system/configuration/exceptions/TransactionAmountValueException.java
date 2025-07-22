package com.example.payment_transfer_system.configuration.exceptions;

import java.math.BigDecimal;

public class TransactionAmountValueException extends RuntimeException {

	private static final long serialVersionUID = -2157412971560985201L;

	public TransactionAmountValueException(BigDecimal amount) {
		super("Transaction amount must be positive number. Current Transaction amount: " + amount);
	}
	
}
