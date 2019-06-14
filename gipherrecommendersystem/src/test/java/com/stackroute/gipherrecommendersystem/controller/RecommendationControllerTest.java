package com.stackroute.gipherrecommendersystem.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.gipherrecommendersystem.domain.Gif;
import com.stackroute.gipherrecommendersystem.domain.Image;
import com.stackroute.gipherrecommendersystem.domain.Rendition;
import com.stackroute.gipherrecommendersystem.domain.User;
import com.stackroute.gipherrecommendersystem.service.RecommendationService;

@RunWith(SpringRunner.class)
@WebMvcTest(RecommendationController.class)
public class RecommendationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RecommendationService recommendationService;

	private Image image;
	private Rendition rendition;
	private Gif gif;
	private User user;
	private List<Gif> gifList;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		image = new Image("http:url", "200", "100", "200");
		rendition = new Rendition(image);
		gif = new Gif("GIF001", "New GIF Title", rendition);
		gifList = new ArrayList<Gif>();
		gifList.add(gif);

		user = new User("jean", "jean@email.com", gifList);
	}

	@After
	public void tearDown() {
		image = null;
		rendition = null;
		gif = null;
		user = null;
	}

	@Test
	public void testGetAllGifFromRecommendation() throws Exception {
		Mockito.when(recommendationService.getAllRecommendation()).thenReturn(gifList);
		mockMvc.perform(get("/api/v1/recommendation", user.getUsername()).contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(gif))).andExpect(status().isOk()).andDo(print());
		Mockito.verify(recommendationService, Mockito.times(1)).getAllRecommendation();
	}

	private static String jsonToString(final Object obj) {
		String result = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
		} catch (JsonProcessingException e) {
			result = "JSON processing error";
		}
		return result;
	}

}
