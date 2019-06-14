package com.stackroute.giphermanager.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSearch {

	@Id
	@JsonProperty("id")
	private String id;

	private String keyword;
	
	@JsonProperty("logtime")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date logTime;

	public UserSearch() {
		super();
	}

	public UserSearch(String id, String keyword, Date logTime) {
		super();
		this.id = id;
		this.keyword = keyword;
		this.logTime = logTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@JsonProperty("logtime")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	public Date getLogTime() {
		return logTime;
	}

	@JsonProperty("logtime")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	@Override
	public String toString() {
		return "UserSearch [id=" + id + ", keyword=" + keyword + ", logTime=" + logTime + "]";
	}
	
}
