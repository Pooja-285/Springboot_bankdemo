package com.demo.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bank.dao.BankDao;
import com.demo.bank.model.Amount;
import com.demo.bank.model.BankAccount;
import com.demo.bank.repository.BankAccountRepository;

@Service
public class BankAccountService {
	
	@Autowired
	private BankAccountRepository bankRepository;
	@Autowired
	private BankDao bankDao;

	public BankAccount createBankAccount(BankAccount account) {

		return bankRepository.save(account);
	}

	public BankAccount updateBankAccount(BankAccount account) {
		return bankRepository.save(account);
		
	}

	public Optional<BankAccount> getBankAccount(Long id) {
		return bankDao.findById(id);
	}
	
	public BankAccount depositAmount(BankAccount account, Amount amount) {
		Double total = account.getAmount()+ amount.getAmount();
		account.setAmount(total);
		return bankRepository.save(account);
		
	}
	
	public List<BankAccount> getAllBankAccounts(){
		return bankDao.findAll();
	}

}
