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
 * Meals
 *
 * A collection of a meals.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Meals extends AbstractSerializable {

	// meal count
	private final int mealCount;

	// list of all meals
	private final List<Meal> meals;

	// list of meals by category
	private final Collection<MealCategory> mealCategories;

	// constructor
	public Meals(List<Restaurant> restaurants, String sortProperty) {
		List<Meal> meals = new ArrayList<>();

		for(Restaurant restaurant : restaurants)
			meals.addAll(restaurant.getMeals());

		this.meals = meals;
		this.mealCount = meals.size();
		this.mealCategories = getMealCategories(meals, sortProperty);
	}

	/**
	 * Categorizes and returns a list of MealCategories based on the sortProperty.
	 *
	 * @param meals
	 * @param sortProperty
	 * @return Collection<MealCategory>
	 */
	private Collection<MealCategory> getMealCategories(List<Meal> meals, String sortProperty) {

		// rating
		if(Meal.RATING.getName().equalsIgnoreCase(sortProperty))
			return getMealCategoriesByRating(meals);

		// tag
		if(Meal.TAG.getName().equalsIgnoreCase(sortProperty))
			return getMealCategoriesByTag(meals);

		// restaurant
		if(Meal.RESTAURANT.getName().equalsIgnoreCase(sortProperty))
			return getMealCategoriesByRestaurant(meals);

		// author
		return getMealCategoriesByName(meals);
	}

	/**
	 * Returns a collection of meal categories by name.
	 *
	 * @param meals
	 * @return Collection<MealCategory>
	 */
	private Collection<MealCategory> getMealCategoriesByName(List<Meal> meals) {
		Collections.sort(meals, Meal.NAME_COMPARATOR);

		Map<String, MealCategory> categoryMap = new LinkedHashMap<>();

		for(Meal meal : meals)
			getMealCategory(categoryMap, meal.getName().substring(0, 1)).addItem(meal);

		return categoryMap.values();
	}

	/**
	 * Returns a collection of meal categories by name.
	 *
	 * @param meals
	 * @return Collection<MealCategory>
	 */
	private Collection<MealCategory> getMealCategoriesByRestaurant(List<Meal> meals) {
		Collections.sort(meals, Meal.RESTAURANT_COMPARATOR);

		Map<String, MealCategory> categoryMap = new LinkedHashMap<>();

		for(Meal meal : meals)
			getMealCategory(categoryMap, meal.getRestaurant().getName()).addItem(meal);

		return categoryMap.values();
	}

	/**
	 * Returns a collection of meals categories by tag.
	 *
	 * @param meals
	 * @return Collection<MealCategory>
	 */
	private Collection<MealCategory> getMealCategoriesByTag(List<Meal> meals) {
		Collections.sort(meals, Meal.NAME_COMPARATOR);

		Map<String, MealCategory> categoryMap = new LinkedHashMap<>();

		for(Meal meal : meals)
			if(!StringUtils.isBlank(meal.getTag()))
				for(String tag : meal.getTag().split(","))
					if(!StringUtils.isBlank(tag))
						getMealCategory(categoryMap, tag).addItem(meal);

		List<MealCategory> mealCategories = new ArrayList<>(categoryMap.values());
		Collections.sort(mealCategories);
		return mealCategories;
	}

	/**
	 * Returns a collection of meal categories by rating.
	 *
	 * @param meals
	 * @return Collection<MealCategory>
	 */
	private Collection<MealCategory> getMealCategoriesByRating(List<Meal> meals) {
		Collections.sort(meals, Meal.RATING_COMPARATOR);

		Map<String, MealCategory> categoryMap = new LinkedHashMap<>();
		getMealCategory(categoryMap, "Unrated");

		for(Meal meal : meals)
		{
			String categoryName = "Unrated";

			if(meal.isCompleted())
				categoryName = meal.getRating() + "/10";

			getMealCategory(categoryMap, categoryName).addItem(meal);
		}

		return categoryMap.values();
	}

	/**
	 * Returns a MealCategory matching the category name.
	 *
	 * @param categoryMap
	 * @param categoryName
	 * @return MealCategory
	 */
	private MealCategory getMealCategory(Map<String, MealCategory> categoryMap, String categoryName) {
		MealCategory mealCategory = categoryMap.get(categoryName.toLowerCase().trim());

		if(mealCategory == null)
		{
			mealCategory = new MealCategory(WordUtils.capitalize(categoryName.trim()));
			categoryMap.put(categoryName.toLowerCase().trim(), mealCategory);
		}

		return mealCategory;
	}

	/**
	 * Returns a list of unrated meals.
	 *
	 * @return List<Meal>
	 */
	public List<Meal> getUnratedMeals() {
		List<Meal> unratedMeals = new ArrayList<>();

		for(Meal meal : meals)
			if(!meal.isCompleted())
				unratedMeals.add(meal);

		Collections.sort(unratedMeals, Meal.NAME_COMPARATOR);

		return unratedMeals;
	}

	/**
	 * Returns the count of all meals.
	 *
	 * @return int
	 */
	public int getMealCount() {
		return mealCount;
	}

	/**
	 * Returns a collection of MealCategories.
	 *
	 * @return Collection<MealCategory>
	 */
	public Collection<MealCategory> getMealCategories() {
		return mealCategories;
	}

}