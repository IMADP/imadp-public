package com.tracktacular.service.tracker.movie;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * MovieServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class MovieServiceImplTest extends TracktacularServiceTestCase {
	Movie movie;
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		movie = new Movie(user);
		movie.setTitle("title");
		movie.setTag("tag");
		movie.setRating(5);
		movie.setTargetDate(new DateTime());

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(movie, movie_movieService);
	}

}