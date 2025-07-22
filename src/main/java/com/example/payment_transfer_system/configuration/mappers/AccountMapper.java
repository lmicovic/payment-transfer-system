package com.example.payment_transfer_system.configuration.mappers;

import org.mapstruct.Mapper;
import com.example.payment_transfer_system.models.Account;
import com.example.payment_transfer_system.models.dto.AccountDTO;

@Mapper(componentModel = "spring")												// Enables Spring to inject this mapper - must use componentModel = "spring" in every Mapper in order to enable Spring to create @Bean from Mapper.
public interface AccountMapper extends CustomMapper<Account, AccountDTO> {		// If we have more than two Mappers defined with componentModel = "spring", we can distinguish them using @Qualifier("className").
																				// Example:    
																				//
																				//		@Qualifier("accountMapper");
																				//		private AccountMapper accountMapper;																	
																				//
																				//		@Qualifier("transactionMapper");
																				// 		private transactionMapper accountMapper;
																				//
																				//
	
}	
