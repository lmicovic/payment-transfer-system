package com.example.payment_transfer_system.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.payment_transfer_system.services.RestService;

public abstract class RestControllerImpl<T, ID, D> implements RestController<D, ID> {

	private final RestService<T, ID, D> service;
	private final Class<T> clazz;
	
	public RestControllerImpl(RestService<T, ID, D> service, Class<T> clazz) {
		this.service = service;
		this.clazz = clazz;
	}

	@Override
	public ResponseEntity<List<D>> findAll() {
		List<D> dtoList = this.service.findAll();
		return new ResponseEntity<List<D>>(dtoList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> findById(ID id) {
		D dtoObject = this.service.findById(id, this.clazz);
		return new ResponseEntity<D>(dtoObject, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> save(D dto) {
		D savedDtoObject = this.service.save(dto);
		return new ResponseEntity<D>(savedDtoObject, HttpStatus.OK);	
	}

	@Override
	public ResponseEntity<?> update(ID id, D dto) {
		
		D updatedDtoObject = this.service.update(id, dto);
		return new ResponseEntity<D>(updatedDtoObject, HttpStatus.OK);
			
	}

	@Override
	public ResponseEntity<String> deleteById(ID id) {
				
		this.service.deleteById(id, clazz);
		return new ResponseEntity<String>(clazz.getSimpleName() + " with ID: " + id + " is deleted.", HttpStatus.OK);
		 
	}
	
}
