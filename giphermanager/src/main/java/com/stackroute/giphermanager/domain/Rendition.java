package com.stackroute.giphermanager.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rendition {

	private Image fixed_height_still;
	private Image original_still;
	private Image fixed_width;
	private Image fixed_height_small_still;
	private Image fixed_height_downsampled;
	private Image preview;
	private Image fixed_height_small;
	private Image downsized_still;
	private Image downsized;
	private Image downsized_large;
	private Image fixed_width_small_still;
	private Image preview_webp;
	private Image fixed_width_still;
	private Image fixed_width_small;
	private Image downsized_small;
	@JsonProperty("fixed_width_downsampled")
	private Image fixed_width_downsampled;
	private Image downsized_medium;
	private Image original;
	private Image fixed_height;
	private Image looping;
	private Image original_mp4;
	private Image preview_gif;

	public Rendition() {
		super();
	}

	public Rendition(Image fixed_width_downsampled) {
		super();
		this.fixed_width_downsampled = fixed_width_downsampled;
	}

	public Image getFixed_width_downsampled() {
		return fixed_width_downsampled;
	}

	public void setFixed_width_downsampled(Image fixed_width_downsampled) {
		this.fixed_width_downsampled = fixed_width_downsampled;
	}

}
