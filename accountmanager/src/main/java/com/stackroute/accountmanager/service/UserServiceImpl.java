package com.stackroute.accountmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.stackroute.accountmanager.domain.User;
import com.stackroute.accountmanager.exception.UserAlreadyExistsException;
import com.stackroute.accountmanager.exception.UserNotFoundException;
import com.stackroute.accountmanager.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	RestTemplate restTemplate;
	
	private UserRepository userRepository;
	String gipherManagerRegisterEndpoint = "http://giphermanagerservice/api/v1/register";

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User saveUser(User user) throws UserAlreadyExistsException {
		User fetchedUser = userRepository.findByUsername(user.getUsername());
		if (fetchedUser != null) {
			throw new UserAlreadyExistsException();
		}
		try {
			if(restTemplate != null) {
				restTemplate.postForObject(gipherManagerRegisterEndpoint, user, User.class);
			}
		} catch (RestClientException e) {
			e.printStackTrace();
			throw new UserAlreadyExistsException();
		}
		return userRepository.save(user);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) throws UserNotFoundException {
		User user = userRepository.findByUsernameAndPassword(username, password);
		if(user == null) {
			throw new UserNotFoundException();
		}
		return user;
	}

}
