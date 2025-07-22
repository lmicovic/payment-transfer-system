package com.example.payment_transfer_system.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AccountDTO implements Serializable {

	private static final long serialVersionUID = -114132369481618490L;

	@Min(value = 1, message = "Id must be a positive number")
	private Long id;
	
	@NotBlank(message = "Firstname is required")
	private String firstname;
	
	@NotBlank(message = "Lastname is required")
	private String lastname;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Email must be valid")
	private String email;
	
	@NotNull(message = "Balance is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Balance must be equal or greater than 0")
	private BigDecimal balance;
	
	public AccountDTO() {

	}

	public AccountDTO(String firstname, String lastname, String email, BigDecimal balance) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.balance = balance;
	}

	public AccountDTO(Long id, String firstname, String lastname, String email, BigDecimal balance) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AccountDTO [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", balance=" + balance + "]";
	}
	
	
	
}
