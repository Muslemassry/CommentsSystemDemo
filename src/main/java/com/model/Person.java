package com.model;

public class Person extends BaseEntity {
	private String personNumber;
	private String email;
	private String phone;
	private String username;
	private String password;
	private String admin;
	
	public Person() {
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String isAdmin) {
		this.admin = isAdmin;
	}
}
