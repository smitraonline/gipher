package com.stackroute.giphermanager.service;

import java.util.List;

import com.stackroute.giphermanager.domain.Gif;
import com.stackroute.giphermanager.domain.User;
import com.stackroute.giphermanager.domain.UserSearch;
import com.stackroute.giphermanager.exception.GifAlreadyExistsException;
import com.stackroute.giphermanager.exception.GifNotFoundException;
import com.stackroute.giphermanager.exception.UserAlreadyExistsException;

public interface UserGifService {

	User saveUserGifToWishList(Gif gif, String username) throws GifAlreadyExistsException;

	User deleteUserGifFromWishList(String username, String gifId) throws GifNotFoundException;

	User updateUserSearchHistory(String username, UserSearch userSearch) throws GifNotFoundException;

	User deleteUserSearchHistory(String username, String historyId) throws GifNotFoundException;

	List<Gif> getAllUserGifFromWishList(String username) throws Exception;

	User registerUser(User user) throws UserAlreadyExistsException;

	List<UserSearch> getAllUserHistories(String username) throws Exception;
}
