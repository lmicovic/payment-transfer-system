package com.example.payment_transfer_system.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface RestController<D, ID> {

	public ResponseEntity<List<D>> findAll();
	public ResponseEntity<?> findById(ID id);
	public ResponseEntity<?> save(D dto);
	public ResponseEntity<?> update(ID id, D dto);
	public ResponseEntity<String> deleteById(ID id);
	
}
