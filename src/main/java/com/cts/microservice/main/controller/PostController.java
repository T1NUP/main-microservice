package com.cts.microservice.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.microservice.main.dto.Like;
import com.cts.microservice.main.dto.Post;
import com.cts.microservice.main.dto.PostComment;
import com.cts.microservice.main.feign.PostReceiver;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class PostController {


	@Autowired
	private PostReceiver postReceiver;

	@GetMapping("/jpa/users/posts")
	public List<Post> getAll() {
		return postReceiver.getAll();
	}

	@GetMapping("/jpa/users/{username}/posts")
	public List<Post> getAllTodos(@PathVariable String username){
		return postReceiver.getAllUserPost(username);
	}

	@GetMapping("/jpa/users/{username}/posts/{id}")
	public Post getTodo(@PathVariable String username, @PathVariable long id){
		return postReceiver.getPost(username, id);
	}

	@GetMapping("/jpa/users/{username}/posts/{id}/comments")
	public List<PostComment> getComments(@PathVariable String username, @PathVariable long id){
		return postReceiver.getComments(username, id);
	}

	@PostMapping("/jpa/users/{username}/posts/{id}/comments")
	public ResponseEntity<Void> addComment(@PathVariable String username, @PathVariable long id, @RequestBody PostComment comment){
		return postReceiver.addComment(username, id, comment);
	}

	@DeleteMapping("/jpa/users/{username}/posts/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
		return postReceiver.deletePost(username, id);
	}
	

	@PutMapping("/jpa/users/{username}/posts/{id}")
	public ResponseEntity<Post> updateTodo(
			@PathVariable String username,
			@PathVariable long id, @RequestBody Post post){
	
		return postReceiver.updatePost(username, id, post);
	}
	
	@PostMapping("/jpa/users/{username}/posts")
	public ResponseEntity<Void> createTodo(
			@PathVariable String username, @RequestBody Post post){
		
		return postReceiver.createPost(username, post); 
	}
	
	/**
	*like API 
	*@author Punit
	*@param LikeDTO
	*@return {@link ResponseEntity}
	*/
	
	@PostMapping("/jpa/like")
	public ResponseEntity<String> addLike(@RequestBody Like like){
		return postReceiver.addLike(like);
				
	}
	
	/**
	*unlike API 
	*@author Punit
	*@param LikeDTO
	*@return {@link ResponseEntity}
	*/
	
	@PostMapping("/jpa/unlike")
	public ResponseEntity<String> unLike(@RequestBody Like like){
		
		return postReceiver.unLike(like);
	}
	
}
