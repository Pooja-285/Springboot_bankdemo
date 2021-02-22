package com.demo.bank.dao;

import java.util.List;
import java.util.Optional;

import com.demo.bank.model.BankAccount;

public interface BankDao {

	public Optional<BankAccount> findById(Long id);
	public void saveBankAccount(BankAccount account);
	public List<BankAccount> findAll();
}
