package com.tracktacular.service.tracker.tv;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * ShowServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ShowServiceImplTest extends TracktacularServiceTestCase {
	Show show;
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		show = new Show(user);
		show.setTitle("title");
		show.setTag("tag");
		show.setRating(5);
		show.setTargetDate(new DateTime());

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(show, tv_showService);
	}

}