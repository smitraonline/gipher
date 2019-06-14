package com.stackroute.giphermanager.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.rabbitmq.domain.MqPayload;
import com.stackroute.giphermanager.config.Producer;
import com.stackroute.giphermanager.domain.Gif;
import com.stackroute.giphermanager.domain.User;
import com.stackroute.giphermanager.domain.UserSearch;
import com.stackroute.giphermanager.exception.GifAlreadyExistsException;
import com.stackroute.giphermanager.exception.GifNotFoundException;
import com.stackroute.giphermanager.exception.UserAlreadyExistsException;
import com.stackroute.giphermanager.repository.UserGifRepository;

@Service
public class UserGifServiceImpl implements UserGifService {
	
	private Producer producer;

	private UserGifRepository userGifRepository;

	@Autowired
	public UserGifServiceImpl(UserGifRepository userGifRepository, Producer producer) {
		super();
		this.userGifRepository = userGifRepository;
		this.producer = producer;
	}

	@Override
	public User registerUser(User user) throws UserAlreadyExistsException {
		User fetchedUser = userGifRepository.findByUsername(user.getUsername());
		if (fetchedUser != null) {
			throw new UserAlreadyExistsException();
		}
		userGifRepository.save(user);
		return user;
	}

	@Override
	public User saveUserGifToWishList(Gif gif, String username) throws GifAlreadyExistsException {
		User fetchedUser = userGifRepository.findByUsername(username);

		List<Gif> fetchedGifs = fetchedUser.getGifList();
		if (fetchedGifs != null) {
			for (Gif gifObj : fetchedGifs) {
				if (gifObj.getId().equals(gif.getId())) {
					throw new GifAlreadyExistsException();
				}
			}
			fetchedGifs.add(gif);
		} else {
			fetchedGifs = new ArrayList<Gif>();
			fetchedGifs.add(gif);
		}

		fetchedUser.setGifList(fetchedGifs);
		userGifRepository.save(fetchedUser);
		
		//update recommendation
		try {
			MqPayload recommendation = new MqPayload(gif.getId(), gif);
			producer.sendMessageToRabbitMqRecommendation(recommendation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fetchedUser;
	}

	@Override
	public User deleteUserGifFromWishList(String username, String gifId) throws GifNotFoundException {
		User fetchedUser = userGifRepository.findByUsername(username);
		List<Gif> fetchedGifs = fetchedUser.getGifList();

		if (fetchedGifs.size() > 0) {
			for (Gif gif : fetchedGifs) {
				if (gifId.equals(gif.getId())) {
					fetchedGifs.remove(gif);
					fetchedUser.setGifList(fetchedGifs);
					userGifRepository.save(fetchedUser);
					break;
				}

			}
		} else {
			throw new GifNotFoundException();
		}
		
		//update recommendation
		try {
			MqPayload recommendation = new MqPayload(gifId, null);
			producer.sendMessageToRabbitMqRecommendation(recommendation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fetchedUser;
	}

	@Override
	public User updateUserSearchHistory(String username, UserSearch userSearch) {
		User fetchedUser = userGifRepository.findByUsername(username);
		List<UserSearch> fetchedSerches = fetchedUser.getSearches();

		if (userSearch != null) {
			if (fetchedSerches == null) {
				fetchedSerches = new ArrayList<UserSearch>();
				fetchedUser.setSearches(fetchedSerches);
			}
			fetchedSerches.add(userSearch);
		}

		System.out.println("User to save: " + fetchedUser);
		userGifRepository.save(fetchedUser);
		return fetchedUser;
	}

	@Override
	public User deleteUserSearchHistory(String username, String historyId) throws GifNotFoundException {
		User fetchedUser = userGifRepository.findByUsername(username);
		List<UserSearch> fetchedHistory = fetchedUser.getSearches();

		if (fetchedHistory.size() > 0) {
			for (UserSearch history : fetchedHistory) {
				if (historyId.equals(history.getId())) {
					fetchedHistory.remove(history);
					fetchedUser.setSearches(fetchedHistory);
					userGifRepository.save(fetchedUser);
					break;
				}

			}
		} else {
			throw new GifNotFoundException();
		}
		return fetchedUser;
	}

	@Override
	public List<UserSearch> getAllUserHistories(String username) throws Exception {
		User fetchedUser = userGifRepository.findByUsername(username);
		return fetchedUser.getSearches();
	}

	@Override
	public List<Gif> getAllUserGifFromWishList(String username) throws Exception {
		User fetchedUser = userGifRepository.findByUsername(username);
		return fetchedUser.getGifList();
	}

}
