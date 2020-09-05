package com.springexceptioncheck.controllers;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.springexceptioncheck.exceptions.StudentException;
import com.springexceptioncheck.exceptions.StudentNameNotFoundException;
import com.springexceptioncheck.model.Student;
import com.springexceptioncheck.services.MyServices;

@RestController
@Validated
@RequestMapping("/students")
public class StudentController{
	
	@Autowired
	MyServices stservice;
	
	@GetMapping
	public List<Student> getAllStudents()
	{
		return stservice.getAllStudents();
	}
	
	@GetMapping("/{id}")
	public Optional<Student> getOneStudent(@PathVariable @Min(1) Long id) 
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
	@PostMapping
	public ResponseEntity<Void> createStudent(@Valid @RequestBody Student st)
	{
		try
		{
			stservice.createStudent(st);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		catch (StudentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
	}
	
	@GetMapping("/user/{username}")
	public List<Student> getStudentByUserName(@PathVariable String username) throws StudentNameNotFoundException
	{
		List<Student> st = stservice.getStudentByUserName(username);
		if(st.isEmpty())
			throw new StudentNameNotFoundException("Student "+ username +" not found");
		return stservice.getStudentByUserName(username);
	}

}
