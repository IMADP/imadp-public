package com.tracktacular.service.tracker.birthday;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * BirthdayServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BirthdayServiceImplTest extends TracktacularServiceTestCase {
	Birthday birthday;
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		birthday = new Birthday(user);
		birthday.setFirstName("name");
		birthday.setLastName("name");
		birthday.setDate(new DateTime());

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(birthday, birthday_birthdayService);
	}

}