package com.springexceptioncheck.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springexceptioncheck.model.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	public List<Student> findByUsername(String username);
	

}
