package com.tracktacular.service.tracker.tv;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * EpisodeServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class EpisodeServiceImplTest extends TracktacularServiceTestCase {
	Episode episode;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();

		Show show = new Show();
		show.setTitle("title");
		show.setTag("tag");
		show.setRating(5);
		show.setTargetDate(new DateTime());
		show.setUser(user);

		episode = new Episode();
		episode.setTitle("title");
		episode.setShow(show);
		episode.setTargetDate(new DateTime());
		episode.setUser(user);

		userService.save(user);
		tv_showService.save(show);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(episode, tv_episodeService);
	}

}
