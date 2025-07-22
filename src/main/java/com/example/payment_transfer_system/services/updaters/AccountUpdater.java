package com.example.payment_transfer_system.services.updaters;

import org.springframework.stereotype.Component;

import com.example.payment_transfer_system.models.Account;

/**
 * Represents the implementation of {@link com.example.payment_transfer_system.services.updaters.Updater Updater} Interface for {@link com.example.payment_transfer_system.models.Account Account} Entity.<br>
 * This class implements functionalities to update Account Entity Object from Database using DTO Account Object retrieved from Network.<br>
 * This is used in {@link com.example.payment_transfer_system.services.AccountService#update(Long, Account) update()} method in {@link com.example.payment_transfer_system.services.AccountService AccountService} Class to update specific Account Data.
 */
@Component
public class AccountUpdater implements Updater<Account> {
	
	/**
	 * Updates the Data of Account Entity Object values from Account DTO Object.<br>
	 * This method is called from {@link com.example.payment_transfer_system.services.AccountService#update(Long, Account) update()} method from {@link com.example.payment_transfer_system.services.AccountService AccountService} Class.
	 * 
	 * @param entity - represents the Account Entity Object. Account Entity Object is Object retrieved from Database witch values will be changed.
	 * @param dto - represents the Account DTO Object. Account DTO Object is object that is retrieved from Network and values from this object is adding to Account Entity Object, to update Account Values in Database.
	 * 
	 * @return entity - updated Account Entity Object.
	 */
	@Override
	public Account applyUpdate(Account entity, Account dtoEntity) {
		
		entity.setFirstname(dtoEntity.getFirstname());
		entity.setLastname(dtoEntity.getLastname());
		entity.setEmail(dtoEntity.getEmail());
		entity.setBalance(dtoEntity.getBalance());
		
		return entity;
		
	}
	
}
