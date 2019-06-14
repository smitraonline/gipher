package com.stackroute.accountmanager.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stackroute.accountmanager.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator {

	@Override
	public Map<String, String> generatetoken(User user) {		
		Map<String, String> map = new HashMap<String, String>();
		
		String jwtToken = Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		map.put("token", jwtToken);
		map.put("message", "User Successfully logged in");
		
		return map;
	}

}
