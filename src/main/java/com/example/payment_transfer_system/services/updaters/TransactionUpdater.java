package com.example.payment_transfer_system.services.updaters;

import org.springframework.stereotype.Component;
import com.example.payment_transfer_system.models.Transaction;

/**
 * Represents the implementation of {@link com.example.payment_transfer_system.services.updaters.Updater Updater} Interface for {@link com.example.payment_transfer_system.models.Transaction Transaction} Entity.<br>
 * This class implements functionalities to update Transaction Entity Object from Database using Transaction DTO Object retrieved from Network.<br>
 * This is used in {@link com.example.payment_transfer_system.services.TransactionService#update(Long, Transaction) update()} method in {@link com.example.payment_transfer_system.services.TransactionService TransactionService} Class to update specific Transaction Data.
 */
@Component
public class TransactionUpdater implements Updater<Transaction>{
	
	@Override
	public Transaction applyUpdate(Transaction entity, Transaction dtoEntity) {
		
		entity.setSourceAccount(dtoEntity.getSourceAccount());
		entity.setDestinationAccount(dtoEntity.getDestinationAccount());
		entity.setAmount(dtoEntity.getAmount());
		entity.setTimestamp(dtoEntity.getTimestamp());
		
		return entity;
	}
	
}
