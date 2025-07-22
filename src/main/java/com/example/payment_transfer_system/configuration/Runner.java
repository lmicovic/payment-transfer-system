package com.example.payment_transfer_system.configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.example.payment_transfer_system.models.dto.AccountDTO;
import com.example.payment_transfer_system.services.AccountService;

@Component
public class Runner implements ApplicationRunner{

	private AccountService accountService;
	
	@Autowired
	public Runner(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		List<AccountDTO> accountsDto = new ArrayList<AccountDTO>();
		accountsDto.add(new AccountDTO("Pera", "Peric", "peraperic@gmail.com", new BigDecimal("100")));
		accountsDto.add(new AccountDTO("Ana", "Anic", "anaanic@gmail.com", new BigDecimal("100")));
		accountsDto.add(new AccountDTO("Ana", "Peric", "anaperic@gmail.com", new BigDecimal("100")));
		
		for (AccountDTO accountDTO : accountsDto) {
			this.accountService.save(accountDTO);
		}

		
	}
	
}
