package com.example.payment_transfer_system.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.payment_transfer_system.configuration.mappers.AccountMapper;
import com.example.payment_transfer_system.models.Account;
import com.example.payment_transfer_system.models.dto.AccountDTO;
import com.example.payment_transfer_system.repositories.AccountRepository;
import com.example.payment_transfer_system.services.updaters.AccountUpdater;

@Service
public class AccountService extends RestServiceImpl<Account, Long, AccountDTO> {
	
	@Autowired
	public AccountService(AccountRepository accountRepository, AccountUpdater accountUpdater, AccountMapper accountMapper) {
		super(accountRepository, accountUpdater, accountMapper, Account.class);
	}
	
	public Optional<AccountDTO> findByIdForUpdate(Long id) {
		Optional<Account> account = ((AccountRepository)super.repository).findByIdForUpdate(id);
		Optional<AccountDTO> accountDto = account.map(super.mapper::toDto);
		return accountDto;
	}
	
}
