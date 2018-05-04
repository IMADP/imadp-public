package com.tracktacular.service.tracker.restaurant;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.service.user.PersistableUserServiceImpl;


/**
 * RestaurantServiceImpl
 *
 * The standard implementation of the RestaurantService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class RestaurantServiceImpl extends PersistableUserServiceImpl<Restaurant> implements RestaurantService {
	private MealService mealService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(mealService);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveMeals(List<Meal> meals) {
		for(Meal meal : meals)
			saveMeal(meal);
	}

	@Override
	protected void onBeforeDelete(Restaurant restaurant) {
		super.onBeforeDelete(restaurant);

		mealService.delete(restaurant.getMeals());
	}

	@Override
	public void saveMeal(Meal meal) {
		mealService.save(meal);

		clearUserCaches(meal.getUser());
	}

	@Override
	public void deleteMeal(Meal meal) {
		mealService.delete(meal);

		clearUserCaches(meal.getUser());
	}

	// getters and setters
	public MealService getMealService() {
		return mealService;
	}

	public void setMealService(MealService mealService) {
		this.mealService = mealService;
	}

}