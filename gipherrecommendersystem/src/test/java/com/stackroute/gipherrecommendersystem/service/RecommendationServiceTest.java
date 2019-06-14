package com.stackroute.gipherrecommendersystem.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.gipherrecommendersystem.domain.Gif;
import com.stackroute.gipherrecommendersystem.domain.Image;
import com.stackroute.gipherrecommendersystem.domain.Recommendation;
import com.stackroute.gipherrecommendersystem.domain.Rendition;
import com.stackroute.gipherrecommendersystem.domain.User;
import com.stackroute.gipherrecommendersystem.repository.RecommendationRepository;

public class RecommendationServiceTest {

	@Mock
	private RecommendationRepository recommendationRepository;

	private Image image;
	private Rendition rendition;
	private Gif gif;
	private List<Gif> gifList = null;
	Recommendation recommendation = null;
	private List<Recommendation> recommendations = null;

	@InjectMocks
	private RecommendationServiceImpl recommendationService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		image = new Image("http:url", "200", "100", "200");
		rendition = new Rendition(image);
		gif = new Gif("GIF001", "New GIF Title", rendition);
		gifList = new ArrayList<Gif>();
		gifList.add(gif);
		recommendation = new Recommendation(gif.getId(), gif);
		recommendations = new ArrayList<Recommendation>();
		recommendations.add(recommendation);
	}

	@After
	public void tearDown() {
		recommendationRepository.deleteAll();
		gifList = null;
		image = null;
		rendition = null;
		gif = null;
	}

	@Test
	public void testGetAllRecommendation() throws Exception {
		Mockito.when(recommendationRepository.findAllByOrderByBookmarkCountDesc()).thenReturn(recommendations);
		List<Gif> fetchedGif = recommendationService.getAllRecommendation();
		assertEquals(fetchedGif, gifList);
		Mockito.verify(recommendationRepository, Mockito.times(1)).findAllByOrderByBookmarkCountDesc();
	}

}
