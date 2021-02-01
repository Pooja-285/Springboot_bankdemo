package com.demo.bank.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class BankResponse {

	Long id;
	Integer statusCode;
	LocalDateTime timeStamp;
	String message;
}
