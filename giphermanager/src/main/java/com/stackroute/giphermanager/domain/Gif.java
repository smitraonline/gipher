package com.stackroute.giphermanager.domain;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Gif {

	@Id
	@JsonProperty("id")
	private String id;

	@JsonProperty("title")
	private String gifName;
	
    private String type;
    //private String id;
    private String slug;
    private String url;
    private String bitly_gif_url;
    private String bitly_url;
    private String embed_url;
    private String username;
    private String source;
    private String rating;
    private String content_url;
    private String source_tld;
    private String source_post_url;
    private boolean is_sticker;
    private String import_datetime;
    private String trending_datetime;
	
	private Rendition rendition;

	public Gif() {
		super();
	}
	
	public Gif(String id, String gifName, Rendition rendition) {
		super();
		this.id = id;
		this.gifName = gifName;
		this.rendition = rendition;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	@JsonProperty("title")
	public String getGifName() {
		return gifName;
	}


	@JsonProperty("title")
	public void setGifName(String gifName) {
		this.gifName = gifName;
	}

	public Rendition getRendition() {
		return rendition;
	}

	public void setRendition(Rendition rendition) {
		this.rendition = rendition;
	}

	public String getBitly_url() {
		return bitly_url;
	}

	public void setBitly_url(String bitly_url) {
		this.bitly_url = bitly_url;
	}

	@Override
	public String toString() {
		return "Gif [id=" + id + ", gifName=" + gifName + ", bitly_url=" + bitly_url + ", rendition=" + rendition + "]";
	}

}
