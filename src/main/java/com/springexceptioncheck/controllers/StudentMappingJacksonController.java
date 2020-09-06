package com.springexceptioncheck.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springexceptioncheck.exceptions.StudentException;
import com.springexceptioncheck.model.Student;
import com.springexceptioncheck.services.MyServices;

@RestController
@RequestMapping("/jackson/")
@Validated
public class StudentMappingJacksonController {
	@Autowired
	MyServices stservice;
	@GetMapping("/{id}")
	public MappingJacksonValue getOneStudent(@PathVariable @Min(1) Long id) 
	{
		
		try
		{
			Student stud = stservice.getOneStudent(id).get();
			Set<String> fields = new HashSet<String>();
			fields.add("username");
			fields.add("firstname");
			fields.add("lastname");
			FilterProvider filterProvider = new SimpleFilterProvider().
					addFilter("studentFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			MappingJacksonValue mapper = new MappingJacksonValue(stud);
			mapper.setFilters(filterProvider);
					
			
			return mapper;
		}
		catch (StudentException e) {
			System.out.println("InsideCatch in controller "+ e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
	
	@GetMapping("params/{id}")
	public MappingJacksonValue getSingleUserDynamic(@PathVariable Long id,
			@RequestParam Set<String> fields)
	{
		try
		{
			Student stud = stservice.getOneStudent(id).get();
			FilterProvider filterProvider = new SimpleFilterProvider()
					.addFilter("studentFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			MappingJacksonValue mapper = new MappingJacksonValue(stud);
			mapper.setFilters(filterProvider);
			return mapper;
		}catch (StudentException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
					
		}
	}
}
