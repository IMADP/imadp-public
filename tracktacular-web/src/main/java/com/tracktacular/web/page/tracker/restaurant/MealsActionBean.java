package com.tracktacular.web.page.tracker.restaurant;

import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.service.tracker.restaurant.Meals;


/**
 * MealsActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-restaurant/meals/by-{sortProperty=name}")
public class MealsActionBean extends RestaurantTrackerActionBean {

	// properties
	private String sortProperty;
	private Meals meals;

	/**
	 * Returns a list of meals.
	 *
	 * @return Meals
	 */
	public Meals getMeals() {
		if(meals == null)
			meals = getTrackerFacade().findActiveMeals(getTrackerUser(), sortProperty);

		return meals;
	}

	// getter and setters
	public String getSortProperty() {
		return sortProperty;
	}

	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}

}