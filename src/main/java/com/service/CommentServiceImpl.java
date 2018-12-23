package com.service;

import java.util.List;

import com.dao.CommentDAO;
import com.model.Comment;

public class CommentServiceImpl implements CommentService {
	private CommentDAO commentDAO;

	public CommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	@Override
	public int addComment(Comment newComment) {
		return  commentDAO.addComment(newComment);
	}

	@Override
	public List<Comment> getComment() {
		// TODO Auto-generated method stub
		return commentDAO.getComment();
	}
}
