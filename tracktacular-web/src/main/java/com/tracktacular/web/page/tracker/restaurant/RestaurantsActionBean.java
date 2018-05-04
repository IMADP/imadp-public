package com.tracktacular.web.page.tracker.restaurant;

import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.service.tracker.restaurant.Restaurants;


/**
 * ShowsActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-restaurant/restaurants/by-{sortProperty=name}")
public class RestaurantsActionBean extends RestaurantTrackerActionBean {

	// properties
	private String sortProperty;
	private Restaurants restaurants;

	/**
	 * Returns a list of restaurants.
	 *
	 * @return Restaurants
	 */
	public Restaurants getRestaurants() {
		if(restaurants == null)
			restaurants = getTrackerFacade().findActiveRestaurants(getTrackerUser(), sortProperty);

		return restaurants;
	}

	// getter and setters
	public String getSortProperty() {
		return sortProperty;
	}

	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}

}