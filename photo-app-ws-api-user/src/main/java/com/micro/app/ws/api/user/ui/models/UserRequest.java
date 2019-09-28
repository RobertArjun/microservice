package com.micro.app.ws.api.user.ui.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequest {
	@NotNull(message = "first name can't be null")
	@Size(min = 2, message = "first name should be minimum 2 charecter")
	private String firstName;
	@NotNull(message = "last name can't be null")
	@Size(min = 2, message = "last name should be minimum 2 charecter")
	private String lastName;
	@NotNull(message = "password can't be null")
	@Size(min = 8, max = 16, message = "password should be minimum 8 charecter or maximum 16 charecter")
	private String password;
	@NotNull(message = "email can't be null")
	@Email
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
