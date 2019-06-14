package com.stackroute.giphermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.giphermanager.domain.Gif;
import com.stackroute.giphermanager.domain.User;
import com.stackroute.giphermanager.domain.UserSearch;
import com.stackroute.giphermanager.exception.GifAlreadyExistsException;
import com.stackroute.giphermanager.exception.GifNotFoundException;
import com.stackroute.giphermanager.exception.UserAlreadyExistsException;
import com.stackroute.giphermanager.service.UserGifService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/")
public class UserGifController {

	private ResponseEntity responseEntity;
	private UserGifService userGifService;

	@Autowired
	public UserGifController(UserGifService userGifService) {
		this.userGifService = userGifService;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) throws UserAlreadyExistsException {
		try {
			userGifService.registerUser(user);
			responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			throw new UserAlreadyExistsException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

	@PostMapping("/user/{username}/gif")
	public ResponseEntity<?> saveUserGifToWishList(@RequestBody Gif gif,
			@PathVariable("username") String username) throws GifAlreadyExistsException {
		try {
			userGifService.saveUserGifToWishList(gif, username);
			responseEntity = new ResponseEntity<>(gif, HttpStatus.CREATED);
		} catch (GifAlreadyExistsException e) {
			throw new GifAlreadyExistsException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

	@DeleteMapping("/user/{username}/{gifId}")
	public ResponseEntity<?> deleteUserGifFromWishList(@PathVariable("username") String username,
			@PathVariable("gifId") String gifId) throws GifNotFoundException {
		try {
			User user = userGifService.deleteUserGifFromWishList(username, gifId);
			responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
		} catch (GifNotFoundException e) {
			throw new GifNotFoundException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

	@PostMapping("/user/{username}/history")
	public ResponseEntity<?> saveUserSearch(@RequestBody UserSearch userSearch,
			@PathVariable("username") String username) throws Exception {
		try {
			System.out.println("User Search: " + userSearch);
			userGifService.updateUserSearchHistory(username, userSearch);
			responseEntity = new ResponseEntity<>(userSearch, HttpStatus.CREATED);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

	@DeleteMapping("/user/{username}/history/{historyId}")
	public ResponseEntity<?> deleteUserSearch(@PathVariable("username") String username,
			@PathVariable("historyId") String historyId) throws GifNotFoundException {
		try {
			User user = userGifService.deleteUserSearchHistory(username, historyId);
			responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
		} catch (GifNotFoundException e) {
			throw new GifNotFoundException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

	@GetMapping("/user/{username}/histories")
	public ResponseEntity<?> getAllUserHistories(@PathVariable("username") String username)
			throws GifNotFoundException {
		try {
			responseEntity = new ResponseEntity<>(userGifService.getAllUserHistories(username),
					HttpStatus.OK);
		} catch (GifNotFoundException e) {
			throw new GifNotFoundException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

	@GetMapping("/user/{username}/gifs")
	public ResponseEntity<?> getAllUserGifFromWishList(@PathVariable("username") String username)
			throws GifNotFoundException {
		try {
			responseEntity = new ResponseEntity<>(userGifService.getAllUserGifFromWishList(username),
					HttpStatus.OK);
		} catch (GifNotFoundException e) {
			throw new GifNotFoundException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}
}
