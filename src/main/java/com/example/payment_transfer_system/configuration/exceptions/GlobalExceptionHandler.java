package com.example.payment_transfer_system.configuration.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> handleEntityNotFoundException(HttpServletRequest request, EntityNotFoundException e) {
		request.setAttribute("exception", e);												// add attribute: exception, in order to cach exception also in Request Interceptor Method fo Logging in File.
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> handleDataIntegrityException(HttpServletRequest request, DataIntegrityViolationException e) {
		
		String message = "Data integrity violation occured.";
		Throwable cause = e.getRootCause();
		
		//----------------------------------------------------------------
		// Read what contraint is violated
		//----------------------------------------------------------------
		if(cause != null && cause.getMessage() != null) {
			String errorMessage = cause.getMessage();
			if(errorMessage.contains("ACCOUNT(EMAIL")) {
				message = "Account with this email already exists.";
			}
			else if(errorMessage.contains("ACCOUNT(ID")) {
				message = "Account with this id already exists.";
			}
		}
		//----------------------------------------------------------------
		
		request.setAttribute("exception", e);												// add attribute: exception, in order to cach exception also in Request Interceptor Method fo Logging in File.
		
		return new ResponseEntity<String>(message, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(TransactionAmountValueException.class)
	public ResponseEntity<String> handleTransactionAmountValueException(HttpServletRequest request, TransactionAmountValueException e) {

		e.printStackTrace();

		request.setAttribute("exception", e);												// add attribute: exception, in order to cach exception also in Request Interceptor Method fo Logging in File.
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InsufficientFundsException.class)
	public ResponseEntity<String> handleInsufficientFundsException(HttpServletRequest request, InsufficientFundsException e) {

		e.printStackTrace();

		request.setAttribute("exception", e);												// add attribute: exception, in order to cach exception also in Request Interceptor Method fo Logging in File.
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(SameAccountTransferException.class)
	public ResponseEntity<String> handleSameAccountTransferException(HttpServletRequest request, SameAccountTransferException e) {
		
		e.printStackTrace();
		request.setAttribute("exception", e);
		
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<String, String>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }
	
}
