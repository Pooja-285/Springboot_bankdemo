package com.demo.bank.constants;

public interface URIConstants {
	
	String CREATE_BANKACCOUNT = "/api/v1/bankAccount";
	String UPDATE_BANKACCOUNT = "/api/v1/bankAccount/{id}";
	String GET_BANKACCOUNT = "/api/v1/bankAccount/{id}";
	String DEPOSIT_AMOUNT = "/api/v1/bankAccount/deposit/{id}";
	String WITHDRAW_AMOUNT = "/api/v1/bankAccount/withdraw/{id}";
	
	String CREATE_BANKACCOUNT_MESSAGE = "Bank account created";
	String UPDATE_BANKACCOUNT_MESSAGE = "Bank account updated";
	String DEPOSIT_BANKACCOUNT_MESSAGE = "Amount deposited successfully in the account";
	String WITHDRAW_BANKACCOUNT_MESSAGE = "Amount withdrawn successfully from the account";

}
