package com.stackroute.gipherrecommendersystem.domain;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {

	@Id
	private String imageId;

	@JsonProperty("url")
	private String url;
	
	private String width;
	private String height;
	private String size;
	private String frames;
	private String mp4;
	private String mp4_size;
	private String webp;
	private String webp_size;
	private String hash;
	
	public Image() {
		super();
	}

	public Image(String url, String width, String height, String size) {
		super();
		this.url = url;
		this.width = width;
		this.height = height;
		this.size = size;
	}

	public Image(String imageId, String url, String width, String height, String size) {
		super();
		this.imageId = imageId;
		this.url = url;
		this.width = width;
		this.height = height;
		this.size = size;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Image [imageId=" + imageId + ", url=" + url + ", width=" + width + ", height=" + height + ", size="
				+ size + "]";
	}
	
}
