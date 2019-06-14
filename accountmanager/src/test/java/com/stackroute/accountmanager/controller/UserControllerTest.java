package com.stackroute.accountmanager.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.accountmanager.domain.User;
import com.stackroute.accountmanager.service.SecurityTokenGenerator;
import com.stackroute.accountmanager.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@MockBean
	RestTemplate restTemplate;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private SecurityTokenGenerator securityTokenGenerator;

	private User user;
	
	@InjectMocks
	private UserController userController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		user = new User();
		user.setUsername("John");
		user.setPassword("password");
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testSaveUser()  throws Exception {
		Mockito.when(userService.saveUser(Mockito.any())).thenReturn(user);
		mockMvc.perform(post("/api/v1/register")
				.contentType(MediaType.APPLICATION_JSON)
					.content(jsonToString(user)))
				.andExpect(status().isCreated())
				.andDo(print());
		Mockito.verify(userService,Mockito.times(1)).saveUser(Mockito.any());
	}
	
	@Test
	public void testLoginSuccess() throws Exception {
		Mockito.when(userService.saveUser(Mockito.any())).thenReturn(user);
		Mockito.when(userService.findByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(user);
		mockMvc.perform(post("/api/v1/login")
				.contentType(MediaType.APPLICATION_JSON)
					.content(jsonToString(user)))
				.andExpect(status().isOk())
				.andDo(print());
		Mockito.verify(userService,Mockito.times(1)).findByUsernameAndPassword(user.getUsername(), user.getPassword());
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
