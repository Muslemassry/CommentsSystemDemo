package com.service;

import java.util.List;

import com.model.Comment;

public interface CommentService {
	public int addComment(Comment newComment);
	public List<Comment> getComment();
}
