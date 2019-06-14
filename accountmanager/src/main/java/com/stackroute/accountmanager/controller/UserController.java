package com.stackroute.accountmanager.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.accountmanager.domain.User;
import com.stackroute.accountmanager.exception.UserAlreadyExistsException;
import com.stackroute.accountmanager.exception.UserNotFoundException;
import com.stackroute.accountmanager.service.SecurityTokenGenerator;
import com.stackroute.accountmanager.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/")
public class UserController {
	
	private ResponseEntity responseEntity;
	private UserService userService;
	private SecurityTokenGenerator securityTokenGenerator;
	
	@Autowired
	public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
		super();
		this.userService = userService;
		this.securityTokenGenerator = securityTokenGenerator;
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
		try {
			userService.saveUser(user);
		} catch (UserAlreadyExistsException e) {
			throw new UserAlreadyExistsException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity = new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity loginUser(@RequestBody User user) throws UserNotFoundException {
		Map<String, String> map = null;
		
		try {
			User userObj = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
			if(userObj.getUsername().equals(user.getUsername())) {
				map = securityTokenGenerator.generatetoken(userObj);
			}
			return responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			throw e;
		} catch (Exception e) {
			return responseEntity = new ResponseEntity<>("Try after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
