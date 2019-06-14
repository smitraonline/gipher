package com.stackroute.giphermanager.repository;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.giphermanager.domain.Gif;
import com.stackroute.giphermanager.domain.Image;
import com.stackroute.giphermanager.domain.Rendition;
import com.stackroute.giphermanager.domain.User;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserGifRepositoryTest {

	@Autowired
	private UserGifRepository userGifRepository;

	private Image image;
	private Rendition rendition;
	private Gif gif;
	private User user;
	private List<Gif> gifList;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		image = new Image("http:url", "200", "100", "200");
		rendition = new Rendition(image);
		gif = new Gif("GIF001", "New GIF Title", rendition);
		gifList = new ArrayList<Gif>();
		gifList.add(gif);

		user = new User("jane", "jane@email.com", gifList);
	}

	@After
	public void tearDown() {
		//userGifRepository.deleteAll();
		userGifRepository.deleteById("jane");
	}

	@Test
	public void testSaveUserTrack() {
		userGifRepository.save(user);
		User fetchedUser = userGifRepository.findByUsername(user.getUsername());
		List<Gif> list = fetchedUser.getGifList();
		Assert.assertEquals(list.get(0).getId(), user.getGifList().get(0).getId());
	}
}
