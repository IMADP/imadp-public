package com.tracktacular.service.tracker.cholesterol;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;

/**
 * CholesterolServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CholesterolServiceImplTest extends TracktacularServiceTestCase {
	Cholesterol cholesterol;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();

		cholesterol = new Cholesterol();
		cholesterol.setHdlCholesterol(10);
		cholesterol.setLdlCholesterol(10);
		cholesterol.setTotalCholesterol(10);
		cholesterol.setTriglycerides(10);
		cholesterol.setDate(new DateTime());
		cholesterol.setUser(user);

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(cholesterol, cholesterol_cholesterolService);
	}

}