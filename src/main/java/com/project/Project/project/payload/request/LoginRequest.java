package com.project.Project.project.payload.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
	private String email;

	@NotBlank
	private String password;

	public String getUsername() {
		return email;
	}

	public void setUsername(String username) {
		this.email = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
