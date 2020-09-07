package com.springexceptioncheck.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springexceptioncheck.DTO.StudentMMDTO;
import com.springexceptioncheck.model.Student;
import com.springexceptioncheck.repository.StudentRepository;

@RestController
@Validated
@RequestMapping("/studentdto")
public class StudentDTOController {
	@Autowired
	StudentRepository srepo;
	@Autowired
	ModelMapper mm;
	
	@GetMapping
	public List<StudentMMDTO>  getAllStudent()
	{
		List<Student> studs = srepo.findAll();
		List<StudentMMDTO> studdto = new ArrayList<>();
		;
		for(Student s:studs)
		{
			System.out.println(s);
			StudentMMDTO sm =  mm.map(s, StudentMMDTO.class);
			System.out.println(sm);
			studdto.add(mm.map(s, StudentMMDTO.class));
			System.out.println(studdto);
		}
		
		return studdto;
	}

}
