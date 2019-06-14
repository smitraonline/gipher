package com.stackroute.giphermanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.giphermanager.domain.User;

public interface UserGifRepository extends MongoRepository<User, String> {
	
	public User findByUsername(String username);

}
