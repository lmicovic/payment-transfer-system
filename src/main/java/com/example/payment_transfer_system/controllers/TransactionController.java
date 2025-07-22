package com.example.payment_transfer_system.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment_transfer_system.models.Transaction;
import com.example.payment_transfer_system.models.dto.TransactionDTO;
import com.example.payment_transfer_system.services.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController extends RestControllerImpl<Transaction, Long, TransactionDTO>{
	
	
	@Autowired
	public TransactionController(TransactionService transactionService) {
		super(transactionService, Transaction.class);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<List<TransactionDTO>> findAll() {
		return super.findAll();
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
		return super.findById(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<?> save(@Valid @RequestBody TransactionDTO dto) {
		return super.save(dto);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @Valid @RequestBody TransactionDTO dto) {
		return super.update(id, dto);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<String> deleteById(@PathVariable(name = "id") Long id) {
		return super.deleteById(id);
	}
	
}
