package com.model;

public class Comment {
	private Integer commentId;
	private Integer personId;
	private String personComment;
	
	public Comment() {
		
	}
	
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public String getPersonComment() {
		return personComment;
	}
	public void setPersonComment(String personComment) {
		this.personComment = personComment;
	}
}
