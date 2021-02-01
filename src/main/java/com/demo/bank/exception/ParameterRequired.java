package com.demo.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ParameterRequired extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_MESSAGE = "Exception";

	public ParameterRequired() {
		super(DEFAULT_MESSAGE);
	}

	public ParameterRequired(String message) {
		super(message);
	}

}
