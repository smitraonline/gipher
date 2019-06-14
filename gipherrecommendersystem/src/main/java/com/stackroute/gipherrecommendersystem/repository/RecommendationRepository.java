package com.stackroute.gipherrecommendersystem.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.gipherrecommendersystem.domain.Recommendation;

public interface RecommendationRepository extends MongoRepository<Recommendation, String> {

	public List<Recommendation> findAllByOrderByBookmarkCountDesc();
}
