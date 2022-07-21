package com.cts.microservice.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Like {
	
	private long id;
	private long idOfPost;
	private String liker;
	
//	public long getId() {
//		return id;
//	}
//	public void setId(long id) {
//		this.id = id;
//	}
//	public String getLiker() {
//		return liker;
//	}
//	public void setLiker(String liker) {
//		this.liker = liker;
//	}
//	public long getIdOfPost() {
//		return idOfPost;
//	}
//	public void setIdOfPost(long idOfPost) {
//		this.idOfPost = idOfPost;
//	}
	
	
	
}
