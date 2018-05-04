package com.tracktacular.service.tracker.restaurant;

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
public class MealServiceImplTest extends TracktacularServiceTestCase {
	Meal meal;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();

		Restaurant restaurant = new Restaurant();
		restaurant.setName("name");
		restaurant.setCity("city");
		restaurant.setState("state");
		restaurant.setTag("tag");
		restaurant.setRating(5);
		restaurant.setTargetDate(new DateTime());
		restaurant.setUser(user);

		meal = new Meal();
		meal.setName("name");
		meal.setRestaurant(restaurant);
		meal.setTargetDate(new DateTime());
		meal.setUser(user);

		userService.save(user);
		restaurant_restaurantService.save(restaurant);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(meal, restaurant_mealService);
	}

}
