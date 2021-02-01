package com.demo.bank.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomeExceptionHandler {

	@ResponseBody
	@ExceptionHandler({ParameterRequired.class, Exception.class})
	public ErrorDetails handleException(HttpServletRequest request, Exception ex) {
		List<String> message = new ArrayList<String>();
		message.add(ex.getLocalizedMessage());
		ErrorDetails details = ErrorDetails.builder().status(HttpStatus.BAD_REQUEST.value())
				.message(message).timeStamp(LocalDateTime.now()).build();
		
		return details;
		
	}
}
