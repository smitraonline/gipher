package com.stackroute.accountmanager.service;

import com.stackroute.accountmanager.domain.User;
import com.stackroute.accountmanager.exception.UserAlreadyExistsException;
import com.stackroute.accountmanager.exception.UserNotFoundException;

public interface UserService {
	public User saveUser(User user) throws UserAlreadyExistsException;
	public User findByUsernameAndPassword(String username, String password) throws UserNotFoundException;

}
