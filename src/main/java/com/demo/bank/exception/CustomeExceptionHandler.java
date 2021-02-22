package com.demo.bank.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomeExceptionHandler extends ResponseEntityExceptionHandler{

	@ResponseBody
	@ExceptionHandler({ParameterRequired.class, Exception.class})
	public ErrorDetails handleException(HttpServletRequest request, Exception ex) {
		List<String> message = new ArrayList<String>();
		message.add(ex.getLocalizedMessage());
		ErrorDetails details = ErrorDetails.builder().status(HttpStatus.BAD_REQUEST.value())
				.message(message).timeStamp(LocalDateTime.now()).build();
		
		return details;
		
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<String> messages = new ArrayList<>();
		for(ObjectError error : ex.getBindingResult().getAllErrors())
			messages.add(error.getDefaultMessage());
		ErrorDetails details = ErrorDetails.builder()
								.status(HttpStatus.BAD_REQUEST.value())
								.message(messages)
								.timeStamp(LocalDateTime.now()).build();
		return new ResponseEntity<Object>(details, HttpStatus.BAD_REQUEST);
	}
}
