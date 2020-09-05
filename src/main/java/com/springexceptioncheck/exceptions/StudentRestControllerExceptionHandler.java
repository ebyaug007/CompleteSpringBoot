package com.springexceptioncheck.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentRestControllerExceptionHandler {
	
	@ExceptionHandler(StudentNameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public StudentExceptionDetails studentNameNotFound(StudentNameNotFoundException ex)
	{
		return new StudentExceptionDetails(new Date(), "This is from @RestControllerAdvice",
				ex.getMessage());
	}

}
