package com.stackroute.giphermanager.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.giphermanager.config.Producer;
import com.stackroute.giphermanager.domain.Gif;
import com.stackroute.giphermanager.domain.Image;
import com.stackroute.giphermanager.domain.Rendition;
import com.stackroute.giphermanager.domain.User;
import com.stackroute.giphermanager.exception.GifAlreadyExistsException;
import com.stackroute.giphermanager.exception.GifNotFoundException;
import com.stackroute.giphermanager.repository.UserGifRepository;

public class UserGifServiceTest {

	@Mock
	private UserGifRepository userGifRepository;
	
	@Mock
	Producer producer;

	private Image image;
	private Rendition rendition;
	private Gif gif;
	private User user;
	private List<Gif> gifList = null;

	@InjectMocks
	private UserGifServiceImpl userGifService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		image = new Image("http:url", "200", "100", "200");
		rendition = new Rendition(image);
		gif = new Gif("GIF001", "New GIF Title", rendition);
		gifList = new ArrayList<Gif>();
		gifList.add(gif);

		user = new User("jean", "jean@email.com", gifList);
	}

	@After
	public void tearDown() {
		userGifRepository.deleteAll();
		gifList = null;
		image = null;
		rendition = null;
		gif = null;
		user = null;
	}

	@Test
	public void testSaveUserGifSuccess() throws GifAlreadyExistsException {
		user = new User("jane", "jane@email.com", null);
		Mockito.when(userGifRepository.findByUsername(user.getUsername())).thenReturn(user);
		User fetchedUser = userGifService.saveUserGifToWishList(gif, user.getUsername());
		assertEquals(fetchedUser, user);
		Mockito.verify(userGifRepository, Mockito.times(1)).findByUsername(user.getUsername());
		Mockito.verify(userGifRepository, Mockito.times(1)).save(user);
	}

	@Test
	public void testDeleteUserGifFromWishList() throws GifNotFoundException {
		Mockito.when(userGifRepository.findByUsername(user.getUsername())).thenReturn(user);
		User fetchedUser = userGifService.deleteUserGifFromWishList(user.getUsername(), gif.getId());
		assertEquals(fetchedUser, user);
		Mockito.verify(userGifRepository, Mockito.times(1)).findByUsername(user.getUsername());
		Mockito.verify(userGifRepository, Mockito.times(1)).save(user);
	}

	@Test
	public void testGetAllUserTrackFromWishList() throws Exception {
		Mockito.when(userGifRepository.findByUsername(user.getUsername())).thenReturn(user);
		List<Gif> fetchedGif = userGifService.getAllUserGifFromWishList(user.getUsername());
		assertEquals(fetchedGif, gifList);
		Mockito.verify(userGifRepository, Mockito.times(1)).findByUsername(user.getUsername());
	}

}
