package com.service;

import com.model.Person;

public interface PersonService {
	public Person doLogin(String username, String password);
	public int registerPerson(Person person);
}
