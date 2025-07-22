package com.example.payment_transfer_system.services;

import java.util.List;

/**
 * Represent Specification for Rest Services.
 * 
 * @param <T> - Entity class.
 * @param <ID> - ID class.
 * @param <D> - DTO class.
 */

public interface RestService<T, ID, D> {

	public List<D> findAll();
	public D findById(ID id, Class<T> clazz);
	public D save(D entity); 
	public D update(ID id, D dto);
	public void deleteById(ID id, Class<T> clazz);
	
}
