package com.stackroute.gipherrecommendersystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.gipherrecommendersystem.domain.Gif;
import com.stackroute.gipherrecommendersystem.domain.Recommendation;
import com.stackroute.gipherrecommendersystem.repository.RecommendationRepository;

@Service
public class RecommendationServiceImpl implements RecommendationService {
	
	private RecommendationRepository recommendationRepository;

	@Autowired
	public RecommendationServiceImpl(RecommendationRepository userGifRepository) {
		super();
		this.recommendationRepository = userGifRepository;
	}

	@Override
	public void updateRecommendation(Recommendation recommendation) {
		Recommendation fetchedRecommendation;
		Optional<Recommendation> optional = recommendationRepository.findById(recommendation.getId());
		if(optional.isPresent()) {
			fetchedRecommendation = optional.get();
			int currentCount = fetchedRecommendation.getBookmarkCount();
			if(recommendation.getGif()!=null) {
				currentCount++;
				fetchedRecommendation.setBookmarkCount(currentCount);
			} else if(currentCount>1) {
				currentCount--;
				fetchedRecommendation.setBookmarkCount(currentCount);
			} else {
				recommendationRepository.deleteById(fetchedRecommendation.getId());
				return;
			}
		} else {
			fetchedRecommendation = new Recommendation(recommendation.getId(), recommendation.getGif());
			fetchedRecommendation.setBookmarkCount(1);
		}
		recommendationRepository.save(fetchedRecommendation);
	}

	@Override
	public List<Gif> getAllRecommendation() {
		List<Gif> gifs = new ArrayList<Gif>();
		//List<Recommendation> recommendations = recommendationRepository.findAll();
		List<Recommendation> recommendations = recommendationRepository.findAllByOrderByBookmarkCountDesc();
		// TODO -- improve logic
		for (Recommendation recommendation : recommendations) {
			if (recommendation.getGif() != null) {
				gifs.add(recommendation.getGif());
			}
		}
		return gifs;
	}

}
