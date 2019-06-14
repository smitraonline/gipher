package com.stackroute.accountmanager.service;

import java.util.Map;

import com.stackroute.accountmanager.domain.User;

public interface SecurityTokenGenerator {
	
	Map<String, String> generatetoken(User user);
}
