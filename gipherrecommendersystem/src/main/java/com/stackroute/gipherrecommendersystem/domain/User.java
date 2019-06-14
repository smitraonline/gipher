package com.stackroute.gipherrecommendersystem.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "User")
public class User {
	
	@Id
	private String username;
	private String email;
	//private String password;
	
	private List<Gif> gifList;

	public User() {
		super();
	}

	public User(String username, String email, List<Gif> gifList) {
		super();
		this.username = username;
		this.email = email;
		this.gifList = gifList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * public String getPassword() { return password; }
	 * 
	 * public void setPassword(String password) { this.password = password; }
	 */

	public List<Gif> getGifList() {
		return gifList;
	}

	public void setGifList(List<Gif> gifList) {
		this.gifList = gifList;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", gifList=" + gifList + "]";
	}
	
}
