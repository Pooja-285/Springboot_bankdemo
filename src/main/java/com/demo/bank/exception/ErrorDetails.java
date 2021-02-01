package com.demo.bank.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorDetails {

	Integer status;
	List<String> message;
	LocalDateTime timeStamp;
}
