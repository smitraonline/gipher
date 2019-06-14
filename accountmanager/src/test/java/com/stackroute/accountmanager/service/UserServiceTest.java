package com.stackroute.accountmanager.service;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.stackroute.accountmanager.domain.User;
import com.stackroute.accountmanager.exception.UserAlreadyExistsException;
import com.stackroute.accountmanager.exception.UserNotFoundException;
import com.stackroute.accountmanager.repository.UserRepository;

public class UserServiceTest {
	
	@Mock
	RestTemplate restTemplate;
	
	@Mock
	private UserRepository userRepository;
	private User user;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setUsername("testuser");
		user.setPassword("password");
	}
	
	@After
	public void tearDown() {
		userRepository.deleteAll();
		user = null;
	}
	
	@Test
	public void testSaveSaveUserSuccess() throws UserAlreadyExistsException {
		Mockito.when(userRepository.save(user)).thenReturn(user);
		Mockito.when(restTemplate.postForObject(Mockito.any(), Mockito.eq(user), Mockito.eq(User.class))).thenReturn(user);
		User fetchedUser = userService.saveUser(user);
		assertEquals(fetchedUser, user);
		Mockito.verify(userRepository,Mockito.times(1)).save(user);
	}
	
	@Test
	public void testFindByUsernameAndPassword() throws UserNotFoundException {
		Mockito.when(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(user);
		User fetchedUser = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		assertEquals(user.getUsername(), fetchedUser.getUsername());
		Mockito.verify(userRepository,Mockito.times(1)).findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
}
