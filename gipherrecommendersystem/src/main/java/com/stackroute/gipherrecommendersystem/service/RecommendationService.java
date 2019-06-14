package com.stackroute.gipherrecommendersystem.service;

import java.util.List;

import com.stackroute.gipherrecommendersystem.domain.Gif;
import com.stackroute.gipherrecommendersystem.domain.Recommendation;

public interface RecommendationService {

	void updateRecommendation(Recommendation recommendation);

	List<Gif> getAllRecommendation();

}
