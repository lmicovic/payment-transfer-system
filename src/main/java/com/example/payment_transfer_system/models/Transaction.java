package com.example.payment_transfer_system.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)								// Many Transactions can be related to same senderAccount.
	@JoinColumn(name = "source_account_id", nullable = false)
	private Account sourceAccount;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "destination_account_id", nullable = false)	// // Many Transactions can be related to same destinationsAccount.
	private Account destinationAccount;
	
	@Column(name = "amount", precision = 19, scale = 2, nullable = false)
	private BigDecimal amount;
	
	@Column(name = "timestamp", nullable = false)
	private LocalDateTime timestamp;
	
	public Transaction() {

	}

	public Transaction(Account sourceAccount, Account destinationAccount, BigDecimal amount, LocalDateTime timestamp) {
		this.sourceAccount = sourceAccount;
		this.destinationAccount = destinationAccount;
		this.amount = amount;
		this.timestamp = timestamp;
	}

	public Transaction(Long id, Account sourceAccount, Account destinationAccount, BigDecimal amount, LocalDateTime timestamp) {
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

	public Account getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(Account sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public Account getDestinationAccount() {
		return destinationAccount;
	}

	public void setDestinationAccount(Account destinationAccount) {
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

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", sourceAccount=" + sourceAccount + ", destinationAccount=" + destinationAccount + ", amount=" + amount + ", timestamp=" + timestamp + "]";
	}
	
}
