package com.springexceptioncheck.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.springexceptioncheck.exceptions.StudentException;
import com.springexceptioncheck.model.Student;
import com.springexceptioncheck.model.Views;
import com.springexceptioncheck.services.MyServices;

@RestController
@RequestMapping("/jsonview/")
@Validated
public class StudentJsonViewController {
	@Autowired
	MyServices stservice;
	@JsonView(Views.External.class)
	@GetMapping("/external/{id}")
	public Optional<Student> getOneStudentExternal(@PathVariable @Min(1) Long id) 
	{
		
		try
		{
			return stservice.getOneStudent(id);
		}
		catch (StudentException e) {
			System.out.println("InsideCatch in controller "+ e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
	@JsonView(Views.Internal.class)
	@GetMapping("/internal/{id}")
	public Optional<Student> getOneStudentInternal(@PathVariable @Min(1) Long id) 
	{
		
		try
		{
			return stservice.getOneStudent(id);
		}
		catch (StudentException e) {
			System.out.println("InsideCatch in controller "+ e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}

}
