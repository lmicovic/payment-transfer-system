package com.example.payment_transfer_system.services.updaters;

/**
 * Used to update Entity Object values from Data Transfer Object values.<br>
 * Maps all attributes from DTO Object to Entity Objects.<br>
 * Update implementation(functionality) must be implemented in Updater Class that implements this Interface.
 * 
 * @param <T> entity - object from Database.
 * @param <D> dto - object received from Network.
 */
public interface Updater<T> {

	public T applyUpdate(T entity, T dto);
	
}
