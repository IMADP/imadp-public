package com.tracktacular.service.admin;

import org.junit.Test;

import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * TracktacularAdminFacadeImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TracktacularAdminFacadeImplTest extends TracktacularServiceTestCase {

	@Override
	public void before() throws Exception {
		User user = new User();
		user.setUid(accountFacade.getDemoUserUid());

		userService.save(user);
	}

	@Test
	public void insertDemoData() {
		adminFacade.insertTrackerDemoData();
		adminFacade.insertTrackerDemoData();
	}

}
