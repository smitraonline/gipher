package com.stackroute.gipherrecommendersystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.gipherrecommendersystem.exception.GifNotFoundException;
import com.stackroute.gipherrecommendersystem.service.RecommendationService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/")
public class RecommendationController {

	private ResponseEntity responseEntity;
	private RecommendationService recommendationService;

	@Autowired
	public RecommendationController(RecommendationService recommendationService) {
		this.recommendationService = recommendationService;
	}

	@GetMapping("/recommendation")
	public ResponseEntity<?> getAllGifFromRecommendation()
			throws GifNotFoundException {
		try {
			responseEntity = new ResponseEntity<>(recommendationService.getAllRecommendation(),
					HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}
}
