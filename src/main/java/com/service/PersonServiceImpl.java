package com.service;

import com.dao.PersonDAO;
import com.model.Person;

public class PersonServiceImpl implements PersonService {
	private PersonDAO personDAO;

	public PersonDAO getPersonDAO() {
		return personDAO;
	}

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	public Person doLogin(String username, String password) {
		return personDAO.getPerson(username, password);
	}

	@Override
	public int registerPerson(Person newPerson) {
		return personDAO.addPerson(newPerson);
		
	}
}
