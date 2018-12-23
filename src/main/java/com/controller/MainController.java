package com.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.Comment;
import com.model.Person;
import com.service.CommentService;
import com.service.PersonService;

@RestController
public class MainController {
	@Autowired
	private CommentService commentService;
	
	@Autowired PersonService personService;
	
	@GetMapping("/getComments")
    public List<Comment> getComments() {
        return commentService.getComment();
    }
	
	@PostMapping("/addComment")
	public Comment addComment(Comment newComment) {
		commentService.addComment(newComment);
		return newComment;
	}
	
	@GetMapping(value = "/username")
	@ResponseBody
	public String currentUserName(Principal principal) {
		return principal.getName();
	}
	
	/*@GetMapping("/getPerson")
    public Person login(String username, String password) {
        return personService.doLogin(username, password);
    }*/
	
	@PostMapping("/register")
	public boolean register(String username, String password, String email, String phone) {
		Person person = new Person();
		person.setUsername(username);
		person.setPassword(password);
		person.setEmail(email);
		person.setPhone(phone);
		int newId = personService.registerPerson(person);
		if (newId < 1) {
			return false;
		} else {
			return true;
		}
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
}
