package com.tracktacular.service.account;

import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * PreferencesServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class PreferencesServiceImplTest extends TracktacularServiceTestCase {
	Preferences preferences;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();

		preferences = new Preferences(user);

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(preferences, preferencesService);
	}	

}