package com.tracktacular.service.tracker.restaurant;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * RestaurantTrackerFacade
 *
 * Provides functionality for restaurant tracking.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface RestaurantTrackerFacade extends TrackerFacade {

	/**
	 * Gets a restaurant by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Restaurant
	 */
	public Restaurant getRestaurant(User user, String uid);

	/**
	 * Saves a restaurant.
	 *
	 * @param restaurant
	 */
	public void saveRestaurant(Restaurant restaurant);

	/**
	 * Deletes a restaurant.
	 *
	 * @param restaurant
	 */
	public void deleteRestaurant(Restaurant restaurant);

	/**
	 * Gets a meal by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Meal
	 */
	public Meal getMeal(User user, String uid);

	/**
	 * Saves a meal.
	 *
	 * @param meal
	 */
	public void saveMeal(Meal meal);

	/**
	 * Saves a list of meals.
	 *
	 * @param meals
	 */
	public void saveMeals(List<Meal> meals);

	/**
	 * Deletes a meal.
	 *
	 * @param meal
	 */
	public void deleteMeal(Meal meal);

	/**
	 * Finds a List of active Restaurants for a User.
	 *
	 * @param user
	 * @param sortProperty
	 * @return Restaurants
	 */
	public Restaurants findActiveRestaurants(User user, String sortProperty);

	/**
	 * Finds a List of active Meals for a User.
	 *
	 * @param user
	 * @param sortProperty
	 * @return Meals
	 */
	public Meals findActiveMeals(User user, String sortProperty);

	/**
	 * Finds a List of deleted Restaurants for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Restaurant>
	 */
	public List<Restaurant> findDeletedRestaurants(User user, CriteriaParams<Restaurant> criteriaParams);

	/**
	 * Finds the count of all deleted Restaurants for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedRestaurantCount(User user);

}