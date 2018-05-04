package com.tracktacular.service.tracker.restaurant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import com.imadp.core.AbstractSerializable;


/**
 * Restaurants
 *
 * A collection of a restaurants.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Restaurants extends AbstractSerializable {

	// restaurant count
	private final int restaurantCount;

	// list of all restaurants
	private final List<Restaurant> restaurants;

	// list of restaurants by category
	private final Collection<RestaurantCategory> restaurantCategories;

	// constructor
	public Restaurants(List<Restaurant> restaurants, String sortProperty) {
		this.restaurants = restaurants;
		this.restaurantCount = restaurants.size();
		this.restaurantCategories = getRestaurantCategories(restaurants, sortProperty);
	}

	/**
	 * Categorizes and returns a list of RestaurantCategories based on the sortProperty.
	 *
	 * @param restaurants
	 * @param sortProperty
	 * @return Collection<RestaurantCategory>
	 */
	private Collection<RestaurantCategory> getRestaurantCategories(List<Restaurant> restaurants, String sortProperty) {

		// city
		if(Restaurant.CITY.getName().equalsIgnoreCase(sortProperty))
			return getRestaurantCategoriesByCity(restaurants);

		// state
		if(Restaurant.STATE.getName().equalsIgnoreCase(sortProperty))
			return getRestaurantCategoriesByState(restaurants);

		// tag
		if(Restaurant.TAG.getName().equalsIgnoreCase(sortProperty))
			return getRestaurantCategoriesByTag(restaurants);

		// rating
		if(Restaurant.RATING.getName().equalsIgnoreCase(sortProperty))
			return getRestaurantCategoriesByRating(restaurants);

		// author
		return getRestaurantCategoriesByName(restaurants);
	}

	/**
	 * Returns a collection of restaurant categories by name.
	 *
	 * @param restaurants
	 * @return Collection<RestaurantCategory>
	 */
	private Collection<RestaurantCategory> getRestaurantCategoriesByName(List<Restaurant> restaurants) {
		Collections.sort(restaurants, Restaurant.NAME_COMPARATOR);

		Map<String, RestaurantCategory> categoryMap = new LinkedHashMap<>();

		for(Restaurant restaurant : restaurants)
			getRestaurantCategory(categoryMap, restaurant.getName().substring(0, 1)).addItem(restaurant);

		return categoryMap.values();
	}

	/**
	 * Returns a collection of restaurant categories by city.
	 *
	 * @param restaurants
	 * @return Collection<RestaurantCategory>
	 */
	private Collection<RestaurantCategory> getRestaurantCategoriesByCity(List<Restaurant> restaurants) {
		Collections.sort(restaurants, Restaurant.CITY_COMPARATOR);

		Map<String, RestaurantCategory> categoryMap = new LinkedHashMap<>();

		for(Restaurant restaurant : restaurants)
			getRestaurantCategory(categoryMap, restaurant.getCity()).addItem(restaurant);

		return categoryMap.values();
	}

	/**
	 * Returns a collection of restaurant categories by state.
	 *
	 * @param restaurants
	 * @return Collection<RestaurantCategory>
	 */
	private Collection<RestaurantCategory> getRestaurantCategoriesByState(List<Restaurant> restaurants) {
		Collections.sort(restaurants, Restaurant.STATE_COMPARATOR);

		Map<String, RestaurantCategory> categoryMap = new LinkedHashMap<>();

		for(Restaurant restaurant : restaurants)
			getRestaurantCategory(categoryMap, restaurant.getState()).addItem(restaurant);

		return categoryMap.values();
	}

	/**
	 * Returns a collection of restaurant categories by tag.
	 *
	 * @param restaurants
	 * @return Collection<RestaurantCategory>
	 */
	private Collection<RestaurantCategory> getRestaurantCategoriesByTag(List<Restaurant> restaurants) {
		Collections.sort(restaurants, Restaurant.NAME_COMPARATOR);

		Map<String, RestaurantCategory> categoryMap = new LinkedHashMap<>();

		for(Restaurant restaurant : restaurants)
			if(!StringUtils.isBlank(restaurant.getTag()))
				for(String tag : restaurant.getTag().split(","))
					if(!StringUtils.isBlank(tag))
						getRestaurantCategory(categoryMap, tag).addItem(restaurant);

		List<RestaurantCategory> restaurantCategories = new ArrayList<>(categoryMap.values());
		Collections.sort(restaurantCategories);
		return restaurantCategories;
	}

	/**
	 * Returns a collection of restaurant categories by rating.
	 *
	 * @param restaurants
	 * @return Collection<RestaurantCategory>
	 */
	private Collection<RestaurantCategory> getRestaurantCategoriesByRating(List<Restaurant> restaurants) {
		Collections.sort(restaurants, Restaurant.RATING_COMPARATOR);

		Map<String, RestaurantCategory> categoryMap = new LinkedHashMap<>();
		getRestaurantCategory(categoryMap, "Unrated");

		for(Restaurant restaurant : restaurants)
		{
			String categoryName = "Unrated";

			if(restaurant.isCompleted())
				categoryName = restaurant.getRating() + "/10";

			getRestaurantCategory(categoryMap, categoryName).addItem(restaurant);
		}

		return categoryMap.values();
	}

	/**
	 * Returns a RestaurantCategory matching the category name.
	 *
	 * @param categoryMap
	 * @param categoryName
	 * @return RestaurantCategory
	 */
	private RestaurantCategory getRestaurantCategory(Map<String, RestaurantCategory> categoryMap, String categoryName) {
		RestaurantCategory restaurantCategory = categoryMap.get(categoryName.toLowerCase().trim());

		if(restaurantCategory == null)
		{
			restaurantCategory = new RestaurantCategory(WordUtils.capitalize(categoryName.trim()));
			categoryMap.put(categoryName.toLowerCase().trim(), restaurantCategory);
		}

		return restaurantCategory;
	}

	/**
	 * Returns a list of unrated restaurants.
	 *
	 * @return List<Restaurant>
	 */
	public List<Restaurant> getUnratedRestaurants() {
		List<Restaurant> unratedRestaurants = new ArrayList<>();

		for(Restaurant restaurant : restaurants)
			if(!restaurant.isCompleted())
				unratedRestaurants.add(restaurant);

		Collections.sort(unratedRestaurants, Restaurant.NAME_COMPARATOR);

		return unratedRestaurants;
	}

	/**
	 * Returns the count of all restaurants.
	 *
	 * @return int
	 */
	public int getRestaurantCount() {
		return restaurantCount;
	}

	/**
	 * Returns a collection of RestaurantCategories.
	 *
	 * @return Collection<RestaurantCategory>
	 */
	public Collection<RestaurantCategory> getRestaurantCategories() {
		return restaurantCategories;
	}

	/**
	 * Returns true if the given restaurant was found in the restaurants collection, according to name, city, state.
	 *
	 * @param otherRestaurant
	 * @return boolean
	 */
	public boolean hasDuplicate(Restaurant otherRestaurant) {
		for(Restaurant restaurant : restaurants)
			if(StringUtils.equals(toSlug(restaurant.getName()), toSlug(otherRestaurant.getName())))
				if(StringUtils.equals(toSlug(restaurant.getCity()), toSlug(otherRestaurant.getCity())))
					if(StringUtils.equals(toSlug(restaurant.getState()), toSlug(otherRestaurant.getState())))
						return !restaurant.equals(otherRestaurant);

		return false;
	}

}