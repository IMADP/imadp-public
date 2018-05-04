package com.tracktacular.service.tracker.restaurant;

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
public class RestaurantServiceImplTest extends TracktacularServiceTestCase {
	Restaurant restaurant;
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		restaurant = new Restaurant(user);
		restaurant.setName("name");
		restaurant.setTag("tag");
		restaurant.setCity("city");
		restaurant.setState("state");
		restaurant.setRating(5);
		restaurant.setTargetDate(new DateTime());

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(restaurant, restaurant_restaurantService);
	}

}