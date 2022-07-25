//package com.cts.microservice.main.feign;
//
//import java.util.List;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import com.cts.microservice.main.dto.Like;
//import com.cts.microservice.main.dto.Post;
//import com.cts.microservice.main.dto.PostComment;
//
//@FeignClient(value = "PostFeignCommunicator", url = "http://localhost:8082")
//public interface PostReceiver {
//
//
//	@GetMapping(value = "/post/users/posts")
//	public List<Post> getAll();
//
//	@GetMapping("/post/users/{username}/posts")
//	public List<Post> getAllUserPost(@PathVariable String username);
//
//	@GetMapping("/post/users/{username}/posts/{id}")
//	public Post getPost(@PathVariable String username, @PathVariable long id);
//
//	@GetMapping("/post/users/{username}/posts/{id}/comments")
//	public List<PostComment> getComments(@PathVariable String username, @PathVariable long id);
//
//	@PostMapping(value = "/post/users/{username}/posts/{id}/comments")
//	public ResponseEntity<Void> addComment(@PathVariable String username, @PathVariable long id,
//			@RequestBody PostComment comment);
//
//	@DeleteMapping("/post/users/{username}/posts/{id}")
//	public ResponseEntity<Void> deletePost(@PathVariable String username, @PathVariable long id);
//
//	@PutMapping("/post/users/{username}/posts/{id}")
//	public ResponseEntity<Post> updatePost(@PathVariable String username, @PathVariable long id,
//			@RequestBody Post post);
//	
//	@PostMapping("/post/users/{username}/posts")
//	public ResponseEntity<Void> createPost(
//			@PathVariable String username, @RequestBody Post post);
//	
//	@PostMapping("/post/like")
//	public ResponseEntity<String> addLike(@RequestBody Like like);
//	
//	@PostMapping("/post/unlike")
//	public ResponseEntity<String> unLike(@RequestBody Like like);
//}
