package com.demo.bank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@Table(name = "bank_account")
@Entity
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String accountNumber;
	private Double amount;
	private AccountType accountType;
}
