package com.demo.bank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Table(name = "bank_account")
@Entity
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountId;
	@NotBlank(message = "FirstName must not be null")
	private String firstName;
	@NotBlank(message = "LastName must not be null")
	private String lastName;
	@NotBlank(message = "PhoneNumber must not be null")
	private String phoneNumber;
	@NotBlank(message = "AccountNumber must not be null")
	private String accountNumber;
	@NotNull
	private Double amount;
	@NotNull
	private AccountType accountType;
	
	public BankAccount() {
		super();
	}
	
	
	
	
	
	
	
}
