package com.springexceptioncheck.DTO;

public class StudentMMDTO {

	private Long id;
	private String username;



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


	public StudentMMDTO(Long id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public StudentMMDTO() {
	}

	@Override
	public String toString() {
		return "StudentMMDTO [id=" + id + ", username=" + username + "]";
	}
	
}
