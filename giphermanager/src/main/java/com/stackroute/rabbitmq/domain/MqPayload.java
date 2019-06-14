package com.stackroute.rabbitmq.domain;

import org.springframework.data.annotation.Id;

import com.stackroute.giphermanager.domain.Gif;

public class MqPayload {

	@Id
	private String id;
	private int bookmarkCount;
	private Gif gif;

	public MqPayload() {
		super();
	}

	public MqPayload(String id, Gif gif) {
		super();
		this.id = id;
		this.gif = gif;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBookmarkCount() {
		return bookmarkCount;
	}

	public void setBookmarkCount(int bookmarkCount) {
		this.bookmarkCount = bookmarkCount;
	}

	public Gif getGif() {
		return gif;
	}

	public void setGif(Gif gif) {
		this.gif = gif;
	}

	@Override
	public String toString() {
		return "Recommendation [id=" + id + ", bookmarkCount=" + bookmarkCount + ", gif=" + gif + "]";
	}
}
