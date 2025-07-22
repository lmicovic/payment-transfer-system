package com.example.payment_transfer_system.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class TransactionDTO implements Serializable {

	private static final long serialVersionUID = 3052150906053654378L;

	@Min(value = 1, message = "Id must be a positive number")
	private Long id;
	
	@NotNull(message = "Source account is required")
//    @Valid
	private AccountDTO sourceAccount;
	
	@NotNull(message = "Destination account is required")
//    @Valid
	private AccountDTO destinationAccount;
	
	@NotNull(message = "Amount is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
	private BigDecimal amount;
	
	private LocalDateTime timestamp;
	
	public TransactionDTO() {

	}

	public TransactionDTO(AccountDTO sourceAccount, AccountDTO destinationAccount, BigDecimal amount, LocalDateTime timestamp) {
		this.sourceAccount = sourceAccount;
		this.destinationAccount = destinationAccount;
		this.amount = amount;
		this.timestamp = timestamp;
	}

	public TransactionDTO(Long id, AccountDTO sourceAccount, AccountDTO destinationAccount, BigDecimal amount, LocalDateTime timestamp) {
		this.id = id;
		this.sourceAccount = sourceAccount;
		this.destinationAccount = destinationAccount;
		this.amount = amount;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AccountDTO getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(AccountDTO sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public AccountDTO getDestinationAccount() {
		return destinationAccount;
	}

	public void setDestinationAccount(AccountDTO destinationAccount) {
		this.destinationAccount = destinationAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "TransactionDTO [id=" + id + ", sourceAccount=" + sourceAccount + ", destinationAccount=" + destinationAccount + ", amount=" + amount + ", timestamp=" + timestamp + "]";
	}
	
	
	
}
