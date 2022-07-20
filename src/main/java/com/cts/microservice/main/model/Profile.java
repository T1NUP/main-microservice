package com.cts.microservice.main.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "profile")
@Data
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String username;
	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column
	private String email;
	@Column
	private String studentnumber;
	@Column
	private String phonenumber;
	@Column
	private String aboutme;
	@Column
	private String avatar;
	@Column
	private String background;

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	// oneToOne Relationship with UserID
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true)
	private UserCredential user;

//	public void setID(Long id) {
//		this.id = id;
//	}
//
//	public long getID() {
//		return id;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public String getFirstname() {
//		return firstname;
//	}
//
//	public void setFirstname(String firstname) {
//		this.firstname = firstname;
//	}
//
//	public String getLastname() {
//		return lastname;
//	}
//
//	public void setLastname(String lastname) {
//		this.lastname = lastname;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getStudentnumber() {
//		return studentnumber;
//	}
//
//	public void setStudentnumber(String studentnumber) {
//		this.studentnumber = studentnumber;
//	}
//
//	public String getPhonenumber() {
//		return phonenumber;
//	}
//
//	public void setPhonenumber(String phonenumber) {
//		this.phonenumber = phonenumber;
//	}
//
//	public String getAboutme() {
//		return aboutme;
//	}
//
//	public void setAboutme(String aboutme) {
//		this.aboutme = aboutme;
//	}
//
//	public void setAvatar(String avatar) {
//		this.avatar = avatar;
//	}
//
//	public String getAvatar() {
//		return avatar;
//	}
//
//	public UserCredential getUser() {
//		return user;
//	}
//
//	public void setUser(UserCredential user) {
//		this.user = user;
//	}

	@Override
	public String toString() {
		return String.format("username: %s, avatar: %s", username, avatar);
	}
}