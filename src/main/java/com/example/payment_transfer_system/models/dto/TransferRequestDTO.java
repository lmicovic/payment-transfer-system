package com.example.payment_transfer_system.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class TransferRequestDTO implements Serializable {

	private static final long serialVersionUID = 5928601657955606359L;
	
	@Min(value = 1, message = "sourceAccountId must be a positive number")
	private Long sourceAccountId;
	
	@Min(value = 1, message = "destinationAccountId must be a positive number")
	@NotNull(message = "destinationAccountId must not be null")
	private Long destinationAccountId;
	
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
	private BigDecimal amount;
	
	public TransferRequestDTO() {

	}

	public TransferRequestDTO(Long sourceAccountId, Long destinationAccountId, BigDecimal amount) {
		this.sourceAccountId = sourceAccountId;
		this.destinationAccountId = destinationAccountId;
		this.amount = amount;
	}

	public Long getSourceAccountId() {
		return sourceAccountId;
	}

	public void setSourceAccountId(Long sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}

	public Long getDestinationAccountId() {
		return destinationAccountId;
	}

	public void setDestinationAccountId(Long destinationAccountId) {
		this.destinationAccountId = destinationAccountId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "TransferRequestDTO [sourceAccountId=" + sourceAccountId + ", destinationAccountId=" + destinationAccountId + ", amount=" + amount + "]";
	}
		
}
