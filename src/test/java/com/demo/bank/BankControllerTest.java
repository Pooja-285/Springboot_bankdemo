package com.demo.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demo.bank.controller.BankController;
import com.demo.bank.model.AccountType;
import com.demo.bank.model.Amount;
import com.demo.bank.model.BankAccount;
import com.demo.bank.service.BankAccountService;
import com.demo.bank.validation.BankAccountValidation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = BankController.class)
public class BankControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockBean
	private BankAccountValidation bankValidation;
	@MockBean
	private BankAccountService bankService;
	
	
	@Test
	public void testGetBankAccount() throws Exception {
		Optional<BankAccount> account = Optional.of(new BankAccount((long) 2,"Pooja", "Singh", "9876543210",
				"111",1000.0,AccountType.Savings));
		Mockito.when(bankService.getBankAccount((long) 2)).thenReturn(account);
		String url = "/api/v1/bankAccount/2";
		MvcResult result = mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
		String actual = result.getResponse().getContentAsString();
		System.out.println(actual);
		
		String expected = objectMapper.writeValueAsString(account);
		System.out.println("Expected :: " +expected);
		assertThat(actual).isEqualToIgnoringWhitespace(expected);
		
	}

	@Test
	public void testCreateBankAccount() throws Exception {
		Optional<BankAccount> account = Optional.of(new BankAccount((long) 1,"Pooja", "Singh", "9876543210",
				"111",1000.0,AccountType.Savings));
		Mockito.when(bankService.createBankAccount(Mockito.any(BankAccount.class))).thenReturn(account.get());
		String uri = "/api/v1/bankAccount";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
				.content(objectMapper.writeValueAsString(account.get()))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isCreated()).andDo(print()).andReturn();
		
		System.out.println("Result :: "+ result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testDepositAmount() throws Exception {
		Optional<BankAccount> account = Optional.of(new BankAccount((long) 1,"Pooja", "Singh", "9876543210",
				"111",1000.0,AccountType.Savings));
		Optional<BankAccount> updatedAccount = Optional.of(new BankAccount((long) 1,"Pooja", "Singh", "9876543210",
				"111",2000.0,AccountType.Savings));
		Amount amount = new Amount(1000.0);
		Mockito.when(bankService.depositAmount(Mockito.eq(account.get()), Mockito.eq(amount))).thenReturn(account.get());
		String uri = "/api/v1/bankAccount/deposit/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)
										.accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
										.content(objectMapper.writeValueAsString(amount))
										.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print()).andReturn();
		
		System.out.println("Result :: "+ result.getResponse().getContentAsString());
		assertEquals((account.get().getAmount()+ amount.getAmount()), updatedAccount.get().getAmount(), "Amount deposited ");
		
	}
}
