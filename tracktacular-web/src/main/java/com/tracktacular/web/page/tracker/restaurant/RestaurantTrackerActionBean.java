package com.tracktacular.web.page.tracker.restaurant;

import java.util.List;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.restaurant.Restaurant;
import com.tracktacular.service.tracker.restaurant.Meal;
import com.tracktacular.service.tracker.restaurant.RestaurantTrackerFacade;
import com.tracktacular.service.tracker.restaurant.RestaurantTrackerPreferences;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * TvTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class RestaurantTrackerActionBean extends TrackerActionBean<RestaurantTrackerFacade, RestaurantTrackerPreferences> {
	private Restaurant restaurant;
	private Meal meal;
	private String sortedMeals;

	@Override
	public Tracker getTracker() {
		return Tracker.RESTAURANT;
	}

	/**
	 * Save or updates a Restaurant.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveRestaurant() {
		Restaurant restaurant = getRestaurant();
		populatePersistableUser(restaurant);
		getTrackerFacade().saveRestaurant(restaurant);

		if(restaurant.isActiveState())
			getContext().addSuccessMessage("restaurant.saveRestaurant.success", restaurant.getName());

		else if(restaurant.isDeletedState())
			getContext().addSuccessMessage("restaurant.deleteRestaurant.success", restaurant.getName());

		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Restaurant.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteRestaurant() {
		Restaurant restaurant = getRestaurant();
		getTrackerFacade().deleteRestaurant(restaurant);
		getContext().addSuccessMessage("restaurant.deleteRestaurant.success", restaurant.getName());
		return getAjaxSourceResolution();
	}

	/**
	 * Save or updates a Meal.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveMeal() {
		Meal meal = getMeal();
		populatePersistableUser(meal);
		getTrackerFacade().saveMeal(meal);
		getContext().addSuccessMessage("restaurant.saveMeal.success", meal.getName());
		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Meal.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteMeal() {
		Meal meal = getMeal();
		getTrackerFacade().deleteMeal(meal);
		getContext().addSuccessMessage("restaurant.deleteMeal.success", meal.getName());
		return getAjaxSourceResolution();
	}

	/**
	 * Sorts Meals.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution sortMeals() {
		List<Meal> sortedCategoriesList = convertObjectList(sortedMeals, Meal.class);
		List<Meal> sortedList = getResortedList(sortedCategoriesList);
		getTrackerFacade().saveMeals(sortedList);
		getContext().addSuccessMessage("restaurant.sortMeals.success");
		return getAjaxSourceResolution();
	}

	// getters and setters
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public String getSortedMeals() {
		return sortedMeals;
	}

	public void setSortedMeals(String sortedMeals) {
		this.sortedMeals = sortedMeals;
	}

}