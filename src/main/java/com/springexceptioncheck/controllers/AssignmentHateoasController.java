package com.springexceptioncheck.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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
@RequestMapping("/students/hateoas")
public class AssignmentHateoasController {
	@Autowired
	AssignmentRepository arepo;
	@Autowired
	StudentRepository srepo;

	@GetMapping("{studid}/assignments")
	public CollectionModel<List<Assignment>> getAllAssignementsOfAStudent(@PathVariable Long studid)
			throws StudentNameNotFoundException {
		Optional<Student> student = srepo.findById(studid);
		if (student.isEmpty())
			throw new StudentNameNotFoundException("No Student");
		List<Assignment> allAssign = student.get().getAssignments();
		Collection<List<Assignment>> assigncollection = Collections.singleton(allAssign);
		return CollectionModel.of(assigncollection);

	}

}
