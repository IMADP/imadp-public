package com.tracktacular.service.tracker.gift;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * GiftServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class GiftServiceImplTest extends TracktacularServiceTestCase {
	Gift gift;
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		gift = new Gift(user);
		gift.setName("name");
		gift.setSender("sender");
		gift.setReceiver("rceiver");
		gift.setOccasion("occasion");
		gift.setDate(new DateTime());

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(gift, gift_giftService);
	}

}