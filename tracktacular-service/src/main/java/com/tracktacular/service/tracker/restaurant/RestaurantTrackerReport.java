package com.tracktacular.service.tracker.restaurant;

import java.util.List;

import com.google.common.collect.Lists;
import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * RestaurantTrackerReport
 *
 * Contains report information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class RestaurantTrackerReport extends AbstractTrackerReport {
	private final Restaurants restaurants;
	private final List<Restaurant> unratedRestaurants;
	private final List<Restaurant> targetDateRestaurants;
	private final List<Meal> targetDateMeals;

    // constructor
    public RestaurantTrackerReport(RestaurantTrackerPreferences preferences, Restaurants restaurants) {
    	this.restaurants = restaurants;
    	this.unratedRestaurants = restaurants.getUnratedRestaurants();
    	this.targetDateRestaurants = Lists.newArrayList();
    	this.targetDateMeals = Lists.newArrayList();

    	if(preferences.isAlertOnTargetDates())
    	{
	    	for(RestaurantCategory restaurantCategory : restaurants.getRestaurantCategories())
	    		for(Restaurant restaurant : restaurantCategory.getItems())
	    		{
	    			if(isCurrentDate(restaurant.getTargetDate()))
	    				targetDateRestaurants.add(restaurant);

	    			if(restaurant.getMeals() != null)
		    			for(Meal meal : restaurant.getMeals())
		    				if(isCurrentDate(meal.getTargetDate()))
		    					targetDateMeals.add(meal);
	    		}
    	}
    }

    @Override
	public boolean isEnabled() {
    	return getRestaurantCount() > 0;
    }

    @Override
    public int getAlertCount() {
    	return targetDateRestaurants.size() + targetDateMeals.size();
    }

    /**
     * Returns the count of unrated restaurants.
     *
     * @return int
     */
    public int getRestaurantCount() {
		return restaurants.getRestaurantCount();
	}

    /**
     * Returns the count of unrated restaurants.
     *
     * @return int
     */
    public int getUnratedRestaurantCount() {
		return unratedRestaurants.size();
	}

    // getters
	public List<Restaurant> getUnratedRestaurants() {
		return unratedRestaurants;
	}

	public List<Restaurant> getTargetDateRestaurants() {
		return targetDateRestaurants;
	}

	public List<Meal> getTargetDateMeals() {
		return targetDateMeals;
	}

}