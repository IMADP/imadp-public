package com.tracktacular.service.tracker.restaurant;

import org.joda.time.DateTime;

import com.imadp.service.user.User;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;



/**
 * RestaurantTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class RestaurantTrackerDemo extends AbstractTrackerDemo {

	private RestaurantTrackerFacade trackerFacade;

	// constructor
	public RestaurantTrackerDemo(RestaurantTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		Restaurant restaurant;
		Meal meal;

		// target date
		restaurant = addRestaurant(user, "Peter Luger", "Steakhouse", null, new DateTime().plusWeeks(1));
		restaurant.setCity("Brooklyn");
		restaurant.setState("NY");
		trackerFacade.saveRestaurant(restaurant);

		// uncompleted
		addRestaurant(user, "Ruth's Chris", "Steakhouse", null, null);

		// completed
		addRestaurant(user, "Olive Garden", "Italian", 8, null);
		addRestaurant(user, "Carmine's", "Italian", 8, null);
		addRestaurant(user, "Zinburger", "American", 7, null);
		addRestaurant(user, "TGI Fridays", "American", 8, null);
		addRestaurant(user, "Joe's Crab Shack", "Seafood", 7, null);
		addRestaurant(user, "Chevy's", "Mexican", 4, null);

		restaurant = addRestaurant(user, "Lobster House", "Seafood", 10, null);
		restaurant.setCity("Cape May");
		restaurant.setState("NJ");
		trackerFacade.saveRestaurant(restaurant);

		restaurant = addRestaurant(user, "Applebees", "American", 3, null);
		restaurant.setNotes("This restaurant seems to be going downhill...");
		trackerFacade.saveRestaurant(restaurant);

		// meals
		restaurant = addRestaurant(user, "Johnny Carino's", "Italian, Steakhouse", 9, null);

		meal = new Meal(user, restaurant);
		meal.setName("NY Strip Steak");
		meal.setNotes("Medium rare with smashed asiago cheese potatoes - awesome.");
		meal.setRating(9);
		trackerFacade.saveMeal(meal);

		meal = new Meal(user, restaurant);
		meal.setName("Pasta Primavera");
		meal.setRating(4);
		trackerFacade.saveMeal(meal);
	}

	/**
	 * Adds a restaurant.
	 *
	 * @param user
	 * @param name
	 * @param tag
	 * @param rating
	 * @param targetDate
	 * @return restaurant
	 */
	private Restaurant addRestaurant(User user, String name, String tag, Integer rating, DateTime targetDate) {
		Restaurant restaurant = new Restaurant(user);
		restaurant.setName(name);
		restaurant.setTag(tag);
		restaurant.setRating(rating);
		restaurant.setTargetDate(targetDate);
		restaurant.setCity("Manhattan");
		restaurant.setState("NY");

		trackerFacade.saveRestaurant(restaurant);

		return restaurant;
	}

}
