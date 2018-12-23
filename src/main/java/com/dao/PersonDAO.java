package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.model.Person;

public class PersonDAO {
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public int addPerson(Person newPerson) {
		int newPersonId = 0;
		String insertSql = "INSERT INTO PERSON ('USERNAME', 'EMAIL', 'UPASSWORD', 'USER_CREATED', 'DATE_CREATED', 'USER_LAST_UPDATE', 'DATE_LAST_UPDATE') VALUES (?, ?, ?, 'ANNONYMOUS', current_timestamp(), 'ANNONYMOUS', current_timestamp())";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		newPersonId = jdbcTemplate.update(insertSql, new Object[] {newPerson.getUsername(), newPerson.getEmail(), newPerson.getPassword()});
		return newPersonId;
	}
	
	public Person getPerson(String username, String password) {
		Person foundPerson = null;
		String query = "SELECT PERSON_NUMBER, USERNAME, EMAIL FROM PERSON WHERE USERNAME = ? AND UPASSWORD = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Person> personList = jdbcTemplate.query(query, new PersonMapper(), new Object[] {username, password});
		if (personList != null && personList.size() > 0) {
			foundPerson = personList.get(0);
		}
		
		return foundPerson;
	}
	
	private class PersonMapper implements RowMapper<Person>{
	    @Override
	    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
	      Person person = new Person();
	      person.setPersonNumber(rs.getString("PERSON_NUMBER"));
	      person.setUsername(rs.getString("USERNAME"));
	      person.setEmail(rs.getString("EMAIL"));
	      return person;
	    }
	}
}
