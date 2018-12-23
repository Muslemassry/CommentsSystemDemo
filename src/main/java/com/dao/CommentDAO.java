package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.model.Comment;

public class CommentDAO {
	
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public int addComment(Comment newComment) {
		int newId = 0;
		String insertSql = "INSERT INTO COMMENT (COMMENT_DESC, PERSON_NUMBER, COMMENT_STATUS, USER_CREATED, DATE_CREATED, USER_LAST_UPDATE, DATE_LAST_UPDATE) \n" + 
				"VALUES(?, ?, 1, 'ANNONYMOUS', current_timestamp(), 'ANNONYMOUS', current_timestamp())";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		newId = jdbcTemplate.update(insertSql, new Object[] {newComment.getPersonComment(), newComment.getPersonId()});
		return newId;
	}
	
	public List<Comment> getComment() {
		List<Comment> commentList = null;
		String queryString = "SELECT COMMENT_ID, COMMENT_DESC, PERSON_NUMBER FROM COMMENT";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		commentList = jdbcTemplate.query(queryString, new CommentMapper());
		return commentList;
		
	}
	
	private class CommentMapper implements RowMapper<Comment>{
	    @Override
	    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
	      Comment comment = new Comment();
	      comment.setCommentId(rs.getInt("COMMENT_ID"));
	      comment.setPersonId(rs.getInt("PERSON_NUMBER"));
	      comment.setPersonComment(rs.getString("COMMENT_DESC"));
	      return comment;
	    }
	}
}
