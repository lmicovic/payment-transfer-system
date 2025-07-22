package com.example.payment_transfer_system.configuration.exceptions;

import java.math.BigDecimal;

import com.example.payment_transfer_system.models.dto.AccountDTO;

public class InsufficientFundsException extends RuntimeException {

	private static final long serialVersionUID = 4316262088055668212L;

	public InsufficientFundsException(AccountDTO accountDto, BigDecimal amount) {
		super(accountDto + " has insufficient fund to complete transaction of: " + amount);
	}
	
}
