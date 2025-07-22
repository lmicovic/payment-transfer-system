package com.example.payment_transfer_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment_transfer_system.models.dto.TransactionDTO;
import com.example.payment_transfer_system.models.dto.TransferRequestDTO;
import com.example.payment_transfer_system.services.TransferService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/transfer")
public class TransferController {

	private TransferService transferService;
	
	@Autowired
	public TransferController(TransferService transferService) {
		this.transferService = transferService;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionDTO> transfer(@Valid @RequestBody TransferRequestDTO transferRequestDto) {
		
		TransactionDTO transactionDto = this.transferService.transfer(transferRequestDto.getSourceAccountId(), transferRequestDto.getDestinationAccountId(), transferRequestDto.getAmount());
		return new ResponseEntity<TransactionDTO>(transactionDto, HttpStatus.OK);		
	}
	
}
