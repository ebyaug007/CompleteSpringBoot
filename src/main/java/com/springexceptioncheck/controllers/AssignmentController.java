package com.springexceptioncheck.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springexceptioncheck.exceptions.StudentException;
import com.springexceptioncheck.exceptions.StudentNameNotFoundException;
import com.springexceptioncheck.model.Assignment;
import com.springexceptioncheck.model.Student;
import com.springexceptioncheck.repository.AssignmentRepository;
import com.springexceptioncheck.repository.StudentRepository;

@RestController
@RequestMapping("/students")
public class AssignmentController {
	@Autowired
	AssignmentRepository arepo;
	@Autowired
	StudentRepository srepo;

	@GetMapping("{studid}/assignments")
	public List<Assignment> getAllAssignementsOfAStudent(@PathVariable Long studid) 
			throws StudentNameNotFoundException
	{
		Optional<Student> student = srepo.findById(studid);
		if(student.isEmpty())
			throw new StudentNameNotFoundException("No Student");
		return student.get().getAssignments();


	}
	
	@PostMapping("{studid}/assignments")
	public Assignment createAssignment(@PathVariable Long studid, 
			@RequestBody Assignment assign) throws StudentNameNotFoundException
	{
		Optional<Student> student = srepo.findById(studid);
		Optional<Assignment> assig = arepo.findById(assign.getId());
		
		if(student.isEmpty())
			throw new StudentNameNotFoundException("No Student");
		if(assig.isPresent())
			throw new StudentNameNotFoundException("Assignment Found");
		Student st = student.get();
		assign.setStudent(st);
		arepo.save(assign);
		return assign;
	}
	@GetMapping("{studid}/assignments/{assignid}")
	public Optional<Assignment> getUserAssignment(@PathVariable("studid") Long studid
			,@PathVariable("assignid") Long assignid) 
			throws StudentNameNotFoundException
	{
		Optional<Student> student = srepo.findById(studid);
		Optional<Assignment> assign = arepo.findById(assignid);
		if(student.isEmpty())
			throw new StudentNameNotFoundException("No Student");
		if(assign.isEmpty() || !assign.get().getStudent().equals(student.get()))
			throw new StudentNameNotFoundException("No Assignment Found");
		return assign;
				


	}
}
