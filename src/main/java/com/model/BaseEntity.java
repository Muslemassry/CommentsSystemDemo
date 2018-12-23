package com.model;

import java.util.Date;

public class BaseEntity {
	private String userCreated;
	private String userLastUpdate;
	private Date dateCreated;
	private Date dateLastUpdate;
	
	public BaseEntity() {
		
	}
	public String getUserCreated() {
		return userCreated;
	}
	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}
	public String getUserLastUpdate() {
		return userLastUpdate;
	}
	public void setUserLastUpdate(String userLastUpdate) {
		this.userLastUpdate = userLastUpdate;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateLastUpdate() {
		return dateLastUpdate;
	}
	public void setDateLastUpdate(Date dateLastUpdate) {
		this.dateLastUpdate = dateLastUpdate;
	}	
}
