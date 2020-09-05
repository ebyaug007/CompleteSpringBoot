package com.springexceptioncheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springexceptioncheck.model.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

}
