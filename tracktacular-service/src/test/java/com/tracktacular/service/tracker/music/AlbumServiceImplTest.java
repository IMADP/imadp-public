package com.tracktacular.service.tracker.music;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * AlbumServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class AlbumServiceImplTest extends TracktacularServiceTestCase {
	Album album;
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		album = new Album(user);
		album.setTitle("title");
		album.setNotes("notes");
		album.setArtist("artist");
		album.setTag("tag");
		album.setRating(5);
		album.setTargetDate(new DateTime());

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(album, music_albumService);
	}

}