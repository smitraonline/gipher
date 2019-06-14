package com.stackroute.giphermanager.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "User")
public class User {
	
	@Id
	private String username;
	private String email;
	
	private List<Gif> gifList;
	private List<UserSearch> searches;

	public User() {
		super();
	}

	public User(String username, String email, List<Gif> gifList) {
		super();
		this.username = username;
		this.email = email;
		this.gifList = gifList;
	}

	public User(String username, String email, List<Gif> gifList, List<UserSearch> searches) {
		super();
		this.username = username;
		this.email = email;
		this.gifList = gifList;
		this.searches = searches;
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

	public List<Gif> getGifList() {
		return gifList;
	}

	public void setGifList(List<Gif> gifList) {
		this.gifList = gifList;
	}

	public List<UserSearch> getSearches() {
		return searches;
	}

	public void setSearches(List<UserSearch> searches) {
		this.searches = searches;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", gifList=" + gifList + ", searches=" + searches
				+ "]";
	}
	
}
