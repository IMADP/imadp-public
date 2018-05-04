package com.tracktacular.service.tracker.music;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * SongServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class SongServiceImplTest extends TracktacularServiceTestCase {
	Song song;
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		song = new Song(user);
		song.setTitle("title");
		song.setAlbum("album");
		song.setNotes("notes");
		song.setArtist("artist");
		song.setTag("tag");
		song.setProgress(100);
		song.setRating(5);
		song.setTargetDate(new DateTime());

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(song, music_songService);
	}

}