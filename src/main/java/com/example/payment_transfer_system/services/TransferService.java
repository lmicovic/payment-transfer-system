package com.example.payment_transfer_system.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.payment_transfer_system.configuration.exceptions.InsufficientFundsException;
import com.example.payment_transfer_system.configuration.exceptions.SameAccountTransferException;
import com.example.payment_transfer_system.configuration.exceptions.TransactionAmountValueException;
import com.example.payment_transfer_system.models.Account;
import com.example.payment_transfer_system.models.dto.AccountDTO;
import com.example.payment_transfer_system.models.dto.TransactionDTO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TransferService {

	private AccountService accountService;
	private TransactionService transactionService;
	
	@Autowired
	public TransferService(AccountService accountService, TransactionService transactionService) {
		this.accountService = accountService;
		this.transactionService = transactionService;
	}
	
	@Transactional		// If happend any exception in this method, then All changes to Database made from this method will be rolled back.
	public TransactionDTO transfer(Long sourceAccountId, Long destinationAccountId, BigDecimal amount) {
				
		// Fetch Accounts
//		AccountDTO sourceAccountDto = this.accountService.findById(sourceAccountId, Account.class);
//		AccountDTO destinationAccountDto = this.accountService.findById(destinationAccountId, Account.class);
		
		AccountDTO sourceAccountDto = this.accountService.findByIdForUpdate(sourceAccountId).orElseThrow(() -> new EntityNotFoundException("Account with id: " + sourceAccountId + " not found."));
		AccountDTO destinationAccountDto = this.accountService.findByIdForUpdate(destinationAccountId).orElseThrow(() -> new EntityNotFoundException("Account with id: " + destinationAccountId + " not found."));
		
		if(sourceAccountDto.getId().equals(destinationAccountDto.getId()) == true) {
			throw new SameAccountTransferException(sourceAccountId, destinationAccountId);
		}
		
		// Validate Fund
		this.validateFund(sourceAccountDto, amount);
		
		// Deduct and Add
		this.deductFund(sourceAccountDto, amount);
		this.addFund(destinationAccountDto, amount);
		
		// Update Accounts
		this.accountService.update(sourceAccountDto.getId(), sourceAccountDto);
		this.accountService.update(destinationAccountDto.getId(), destinationAccountDto);
		
		// Record Transaction
		TransactionDTO transactionDto = new TransactionDTO(sourceAccountDto, destinationAccountDto, amount, LocalDateTime.now());
		transactionDto = this.transactionService.save(transactionDto);
		
		// Log Transaction - in file
		// Logged in TransferInterceptor.afterCompletion() method.
		
		return transactionDto;
		
	}
	
	private void validateFund(AccountDTO sourceAccountDto, BigDecimal amount) {
		
		if(amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new TransactionAmountValueException(amount);
		}
		
		if(sourceAccountDto.getBalance().compareTo(amount) == -1) {			// IfsourceAccountDto.getBalance() < amount than comparteTo() will be -1 
			throw new InsufficientFundsException(sourceAccountDto, amount); 
		}
		
	}
	
	private void deductFund(AccountDTO accountDto, BigDecimal amount) {
		accountDto.setBalance(accountDto.getBalance().subtract(amount));
	}
	
	private void addFund(AccountDTO accountDto, BigDecimal amount) {
		accountDto.setBalance(accountDto.getBalance().add(amount));
	}
	
}
