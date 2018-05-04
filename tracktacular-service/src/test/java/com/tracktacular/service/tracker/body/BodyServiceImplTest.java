package com.tracktacular.service.tracker.body;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;

/**
 * BodyServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BodyServiceImplTest extends TracktacularServiceTestCase {
	Body body;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();

		body = new Body();
		body.setDate(new DateTime());
		body.setUser(user);

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(body, body_bodyService);
	}

}