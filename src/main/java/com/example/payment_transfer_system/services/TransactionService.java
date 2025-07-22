package com.example.payment_transfer_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.payment_transfer_system.configuration.mappers.TransactionMapper;
import com.example.payment_transfer_system.models.Transaction;
import com.example.payment_transfer_system.models.dto.TransactionDTO;
import com.example.payment_transfer_system.repositories.TransactionRepository;
import com.example.payment_transfer_system.services.updaters.TransactionUpdater;

@Service
public class TransactionService extends RestServiceImpl<Transaction, Long, TransactionDTO> {

	@Autowired
	public TransactionService(TransactionRepository transactionRepository, TransactionUpdater transactionUpdater, TransactionMapper transactionMapper) {
		super(transactionRepository, transactionUpdater, transactionMapper, Transaction.class);
	}
	
}
