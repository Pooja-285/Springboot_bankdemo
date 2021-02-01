package com.demo.bank.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bank.constants.BankAccountConstants;
import com.demo.bank.constants.URIConstants;
import com.demo.bank.exception.ParameterRequired;
import com.demo.bank.model.Amount;
import com.demo.bank.model.BankAccount;
import com.demo.bank.response.BankResponse;
import com.demo.bank.service.BankAccountService;
import com.demo.bank.validation.BankAccountValidation;

@RestController
public class BankController {
	
	@Autowired
	private BankAccountService bankService;
	@Autowired
	private BankAccountValidation bankValidation;
	
	@PostMapping(value = URIConstants.CREATE_BANKACCOUNT,
			produces = {"application/json","application/xml"},
			consumes = {"application/json","application/xml"})
	public ResponseEntity<BankResponse> createBankAccount(@RequestBody @Validated BankAccount account) {
		
		bankValidation.validateIfAccountExists(account.getAccountNumber());
		bankService.createBankAccount(account);
		return new ResponseEntity<BankResponse>(BankResponse.builder().id(account.getAccountId())
					.statusCode(BankAccountConstants.SUCCESS_STATUS)
					.timeStamp(LocalDateTime.now())
					.message(URIConstants.CREATE_BANKACCOUNT_MESSAGE).build(), HttpStatus.CREATED);
		
	}
	
	@PutMapping(URIConstants.UPDATE_BANKACCOUNT)
	public ResponseEntity<BankResponse> updateBankAccount(@Validated BankAccount account, @PathVariable("id") Long id){
		bankValidation.validateBankAccountId(id);
		bankService.updateBankAccount(account);
		return new ResponseEntity<BankResponse>(BankResponse.builder().id(account.getAccountId())
				.statusCode(BankAccountConstants.SUCCESS_STATUS)
				.timeStamp(LocalDateTime.now())
				.message(URIConstants.UPDATE_BANKACCOUNT_MESSAGE).build(), HttpStatus.OK);
		
	}
	
	@GetMapping(URIConstants.GET_BANKACCOUNT)
	public BankAccount getBankAccount(@PathVariable("id") Long id) {
		Optional<BankAccount> account = bankService.getBankAccount(id);
		if(account.isPresent())
			return account.get();
		else
			throw new ParameterRequired("BankAccount id : "+id+" is invalid");
		
	}
	
	@PostMapping(URIConstants.DEPOSIT_AMOUNT)
	public ResponseEntity<BankResponse> depositAmount(@RequestBody @Validated Amount amount, @PathVariable("id") Long id){
		BankAccount account = bankValidation.validateAndGetBankAccountById(id);
		Double total = amount.getAmount() + account.getAmount();
		account.setAmount(total);
		bankService.updateBankAccount(account);
		return new ResponseEntity<BankResponse>(BankResponse.builder().id(account.getAccountId())
				.statusCode(BankAccountConstants.SUCCESS_STATUS)
				.timeStamp(LocalDateTime.now())
				.message(URIConstants.DEPOSIT_BANKACCOUNT_MESSAGE).build(), HttpStatus.OK);
	}
	
	@PostMapping(URIConstants.WITHDRAW_AMOUNT)
	public ResponseEntity<BankResponse> withdrawAmount(@RequestBody @Validated Amount amount, @PathVariable("id") Long id){
		BankAccount account = bankValidation.validateAndGetBankAccountById(id);
		if(amount.getAmount() > account.getAmount())
			throw new ParameterRequired("Withdraw amount must be less than account balance");
		Double total = account.getAmount() - amount.getAmount();
		account.setAmount(total);
		bankService.updateBankAccount(account);
		return new ResponseEntity<BankResponse>(BankResponse.builder().id(account.getAccountId())
				.statusCode(BankAccountConstants.SUCCESS_STATUS)
				.timeStamp(LocalDateTime.now())
				.message(URIConstants.WITHDRAW_BANKACCOUNT_MESSAGE).build(), HttpStatus.OK);
	}

}
