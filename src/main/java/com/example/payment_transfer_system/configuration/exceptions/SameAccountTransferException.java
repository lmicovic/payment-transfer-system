package com.example.payment_transfer_system.configuration.exceptions;

public class SameAccountTransferException extends RuntimeException{

	private static final long serialVersionUID = 1165248445292791837L;

	public SameAccountTransferException(Long sourceAccountId, Long destinationAccountId) {
		super("Cannot transfer to the same account. sourceAccoutId: " + sourceAccountId + " - destinationAccountId: " + destinationAccountId);
	}
	
}
