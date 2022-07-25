//package com.cts.microservice.main.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.cts.microservice.main.feign.FollowReceiver;
//
//@RestController
//@CrossOrigin
//public class FollowController {
//
//	@Autowired
//	private FollowReceiver receiver;
//	
//	/**
//	*Followers API 
//	*@author Punit
//	*user searching users he is following
//	*/
//	@PutMapping("/jpa/user/{user}/followuser/{username}")
//	public ResponseEntity<?> followUserWithUsername(@PathVariable String user, @PathVariable String username){
//		return receiver.addFollow(user, username);
//	}
//	
//	@GetMapping("/jpa/{user}/following")
//	public ResponseEntity<?> getAllFollowing(@PathVariable String user) {
//		return receiver.getAllFollow(user);
//	}
//	
//	@PutMapping("/jpa/user/{user}/unfollowuser/{username}")
//	public ResponseEntity<?> unfollowRequest(@PathVariable String user, @PathVariable String username) {
//		return receiver.removeFollow(user, username);
//	}
//}
