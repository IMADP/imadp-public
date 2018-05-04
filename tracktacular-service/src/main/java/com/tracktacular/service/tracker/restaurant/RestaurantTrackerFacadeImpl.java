package com.tracktacular.service.tracker.restaurant;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.joda.time.Interval;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.tracker.AbstractTrackerFacade;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.TrackerDemo;
import com.tracktacular.service.tracker.calendar.CalendarEntry;


/**
 * RestaurantTrackerFacadeImpl
 *
 * The standard implementation of the RestaurantTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class RestaurantTrackerFacadeImpl extends AbstractTrackerFacade
	implements RestaurantTrackerFacade {

	private RestaurantService restaurantService;
	private MealService mealService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(restaurantService);
		Validate.notNull(mealService);
	}

	@Override
	public Restaurant getRestaurant(User user, String uid) {
		return restaurantService.findFirstByUser(user, uid);
	}

	@Override
	public void saveRestaurant(Restaurant restaurant) {
		new RestaurantValidator("restaurant", restaurant, findActiveRestaurants(restaurant.getUser(), Restaurant.NAME.getName())).validate();
		restaurantService.save(restaurant);
	}

	@Override
	public void deleteRestaurant(Restaurant restaurant) {
		restaurantService.delete(restaurant);
	}

	@Override
	public Meal getMeal(User user, String uid) {
		return mealService.findFirstByUser(user, uid);
	}

	@Override
	public void saveMeal(Meal meal) {
		validateMeal(meal);
		restaurantService.saveMeal(meal);
	}

	@Override
	public void saveMeals(List<Meal> meals) {
		for(Meal meal : meals)
			validateMeal(meal);

		restaurantService.saveMeals(meals);
	}

	/**
	 * Validates a Meal.
	 *
	 * @param meal
	 */
	private void validateMeal(Meal meal) {
		new MealValidator("meal", meal).validate();
	}

	@Override
	public void deleteMeal(Meal meal) {
		restaurantService.deleteMeal(meal);
	}

	@Override
	public Restaurants findActiveRestaurants(User user, String sortProperty) {
		List<Restaurant> activeRestaurants = restaurantService.findByUser(
				user, PersistableState.ACTIVE, CriteriaParams.<Restaurant>of(Results.ALL));

		return new Restaurants(activeRestaurants, sortProperty);
	}

	@Override
	public Meals findActiveMeals(User user, String sortProperty) {
		List<Restaurant> activeRestaurants = restaurantService.findByUser(
				user, PersistableState.ACTIVE, CriteriaParams.<Restaurant>of(Results.ALL));

		return new Meals(activeRestaurants, sortProperty);
	}

	@Override
	public List<Restaurant> findDeletedRestaurants(User user, CriteriaParams<Restaurant> criteriaParams) {
		return restaurantService.findByUser(user, PersistableState.DELETED, criteriaParams);
	}

	@Override
	public long findDeletedRestaurantCount(User user) {
		return restaurantService.findCountByUser(user, PersistableState.DELETED);
	}

	@Override
	protected void onDeleteAll(User user) {
		List<Restaurant> restaurants = restaurantService.findByUser(user, CriteriaParams.<Restaurant>of(Results.ALL));

		for(Restaurant restaurant : restaurants)
			deleteRestaurant(restaurant);
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new RestaurantTrackerDemo(this);
	}

	@Override
	public RestaurantTrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		Restaurants restaurants = findActiveRestaurants(user, Restaurant.NAME.getName());
		return new RestaurantTrackerReport(preferences.getTrackers().getRestaurantTrackerPreferences(), restaurants);
	}

	@Override
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences) {
		List<CalendarEntry> calendarEntries = new ArrayList<>();

		// active restaurants
		Restaurants restaurants = findActiveRestaurants(user, Restaurant.NAME.getName());

		for(RestaurantCategory category : restaurants.getRestaurantCategories())
			for(Restaurant restaurant : category.getItems())
			{
				if(restaurant.getTargetDate() != null)
				{
					CalendarEntry calendarEntry = new CalendarEntry(user, restaurant.getTargetDate());
					calendarEntry.setTracker(Tracker.RESTAURANT);
					calendarEntry.setName(String.format("Restaurant: %s", restaurant.getName()));
					calendarEntry.setDescription(restaurant.getTag());
					calendarEntries.add(calendarEntry);
				}

				if(restaurant.getMeals() != null)
					for(Meal meal : restaurant.getMeals())
					{
						if(meal.getTargetDate() != null)
						{
							CalendarEntry calendarEntry = new CalendarEntry(user, meal.getTargetDate());
							calendarEntry.setTracker(Tracker.RESTAURANT);
							calendarEntry.setName(meal.getName());
							calendarEntry.setDescription(restaurant.getName());
							calendarEntries.add(calendarEntry);
						}
					}
			}

		return calendarEntries;
	}

	// getters and setters
	public RestaurantService getRestaurantService() {
		return restaurantService;
	}

	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	public MealService getMealService() {
		return mealService;
	}

	public void setMealService(MealService mealService) {
		this.mealService = mealService;
	}

}