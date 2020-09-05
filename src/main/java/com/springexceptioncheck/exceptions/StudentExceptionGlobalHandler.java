package com.springexceptioncheck.exceptions;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice
public class StudentExceptionGlobalHandler extends ResponseEntityExceptionHandler {
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		
		StudentExceptionDetails sd = new StudentExceptionDetails(new Date(), "Not Correct", ex.getMessage());
		return new ResponseEntity<>(sd,HttpStatus.BAD_REQUEST);
		//return handleExceptionInternal(ex, sd, headers, HttpStatus.NOT_ACCEPTABLE, request);
	}
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		StudentExceptionDetails sd = new StudentExceptionDetails(new Date(), "Defined Method not present", ex.getMessage());
		return new ResponseEntity<>(sd,HttpStatus.METHOD_NOT_ALLOWED);
	}
	@ExceptionHandler(StudentNameNotFoundException.class)
	protected ResponseEntity<Object> handleStudentNameNotFoundException(StudentNameNotFoundException ex
			, WebRequest request) {
		// TODO Auto-generated method stub
		StudentExceptionDetails sd = new StudentExceptionDetails(new Date(), ex.getMessage(),request.getSessionId());
		return new ResponseEntity<>(sd,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request)
	{
		StudentExceptionDetails sd = new StudentExceptionDetails(new Date(), ex.getMessage(),request.getSessionId());
		return new ResponseEntity<>(sd,HttpStatus.NOT_FOUND);
	}

}
