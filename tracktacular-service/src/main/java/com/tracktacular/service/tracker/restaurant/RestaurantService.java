package com.tracktacular.service.tracker.restaurant;

import java.util.List;

import com.imadp.service.user.PersistableUserService;

/**
 * RestaurantService
 *
 * Provides common retrieval operations for Restaurant objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface RestaurantService extends PersistableUserService<Restaurant> {

	/**
	 * Saves an meal.
	 *
	 * @param meal
	 */
	public void saveMeal(Meal meal);

	/**
	 * Saves a list of restaurant meals.
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

}