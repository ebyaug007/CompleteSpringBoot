package com.springexceptioncheck.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springexceptioncheck.exceptions.StudentNameNotFoundException;
import com.springexceptioncheck.model.Assignment;
import com.springexceptioncheck.model.Student;
import com.springexceptioncheck.repository.AssignmentRepository;
import com.springexceptioncheck.repository.StudentRepository;

@RestController
@Validated
@RequestMapping("/students/hateoas")
public class StudentHateoasController {
	@Autowired
	StudentRepository srepo;
	
	@Autowired
	AssignmentRepository arepo;
	
	@GetMapping
	public CollectionModel<List<Student>> getAllUsers() throws StudentNameNotFoundException
	{
		List<Student> allstudent = srepo.findAll();
		for(Student st: allstudent)
		{
			Long id = st.getId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(id).withSelfRel();
			st.add(selfLink);
			
			CollectionModel<List<Assignment>> assignCollection = WebMvcLinkBuilder.methodOn(AssignmentHateoasController.class).
					getAllAssignementsOfAStudent(id);
			Link assignlink = WebMvcLinkBuilder.linkTo(assignCollection).withRel("assignments");
			
			st.add(assignlink);
						
		}
		Collection<List<Student>> studCollection = Collections.singleton(allstudent);
		CollectionModel<List<Student>> finalEntity = CollectionModel.of(studCollection);
		//CollectionModel<Student> finalEntity = new CollectionModel<Student>(allstudent);
		return finalEntity;
	}
	@GetMapping("/{id}")
	public EntityModel<Student> getUserById(@PathVariable Long id)
	{
		Student stud = srepo.findById(id).get();
		Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(stud.getId()).withSelfRel();
		stud.add(selfLink);
		EntityModel<Student> finalEntity = EntityModel.of(stud);
		return finalEntity;
	}

}
