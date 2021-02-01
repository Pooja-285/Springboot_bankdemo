package com.demo.bank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bank.model.BankAccount;
import com.demo.bank.repository.BankAccountRepository;

@Service
public class BankAccountService {
	
	@Autowired
	private BankAccountRepository bankRepository;

	public BankAccount createBankAccount(BankAccount account) {

		return bankRepository.save(account);
	}

	public BankAccount updateBankAccount(BankAccount account) {
		return bankRepository.save(account);
		
	}

	public Optional<BankAccount> getBankAccount(Long id) {
		return bankRepository.findById(id);
	}

}
