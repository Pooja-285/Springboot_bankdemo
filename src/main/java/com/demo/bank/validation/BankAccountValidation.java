package com.demo.bank.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.bank.exception.ParameterRequired;
import com.demo.bank.model.BankAccount;
import com.demo.bank.repository.BankAccountRepository;

@Component
public class BankAccountValidation {
	
	@Autowired
	private BankAccountRepository bankRepository;
	
	public void validateIfAccountExists(String accountNumber) {
		Optional<BankAccount> account = bankRepository.findByAccountNumber(accountNumber);
		if(account.isPresent())
			throw new ParameterRequired("Bank account with accountNumber "+accountNumber +" already exists");
	}
	
	public void validateBankAccountId(Long id) {
		Optional<BankAccount> account = bankRepository.findById(id);
		account.orElseThrow(() -> new ParameterRequired("Bank account with id : "+ id + " is invalid"));
	}

	public BankAccount validateAndGetBankAccountById(Long id) {
		Optional<BankAccount> account = bankRepository.findById(id);
		return account.orElseThrow(() -> new ParameterRequired("Bank account with id : "+ id + " is invalid"));
	}

}
