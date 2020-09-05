package com.springexceptioncheck.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springexceptioncheck.exceptions.StudentException;
import com.springexceptioncheck.model.Student;
import com.springexceptioncheck.repository.StudentRepository;

import jdk.jshell.spi.ExecutionControl.UserException;

@Service
public class MyServices {
	@Autowired
	StudentRepository strepo;
	
	public List<Student> getAllStudents()
	{
		return strepo.findAll();
	}
	
	public Optional<Student> getOneStudent(Long id ) throws StudentException
	{
		Optional<Student> st = strepo.findById(id);
		System.out.println(st);
		if(!st.isPresent())
		{
			System.out.println("InsideCatch in service");
			throw new StudentException("Student not found");
		}
		return st;
		
	}

	public void createStudent(Student st) throws StudentException
	
	{
		Optional<Student> stud = strepo.findById(st.getId());
		if(stud.isPresent())
			throw new StudentException("Student Already exists");
		strepo.save(st);
	
	}

	public List<Student> getStudentByUserName(String username) {
		// TODO Auto-generated method stub
		return strepo.findByUsername(username);
	}
	
	

}
