package com.springexceptioncheck.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFilter;

@Entity
@Table(name = "student")
@JsonFilter("studentFilter")
public class Student extends RepresentationModel {
	@Id
	private Long id;
	@Column(unique = true)
	@NotBlank(message = "Should enter atleast one character")
	private String username;
	private String firstname;
	private String lastname;
	@Column(unique = true)
	private String ssn;

	@OneToMany(mappedBy = "student")
	private List<Assignment> assignments;

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", ssn=" + ssn + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Student(Long id, String username, String firstname, String lastname, String ssn) {

		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.ssn = ssn;
	}

	public Student() {

	}

}
