package com.stackroute.rabbitmq.domain;

import org.springframework.data.annotation.Id;

import com.stackroute.gipherrecommendersystem.domain.Gif;

public class MqPayload {

	@Id
	private String id;
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

	public Gif getGif() {
		return gif;
	}

	public void setGif(Gif gif) {
		this.gif = gif;
	}

	@Override
	public String toString() {
		return "MqPayload [id=" + id + ", gif=" + gif + "]";
	}
}
