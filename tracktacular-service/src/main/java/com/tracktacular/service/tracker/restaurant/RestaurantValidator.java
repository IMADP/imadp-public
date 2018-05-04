package com.tracktacular.service.tracker.restaurant;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * RestaurantValidator
 *
 * Ensures that the restaurant is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.invalidUnique
 *       {objectName}.name.invalidNull
 *       {objectName}.name.invalidMaxLength
 *       {objectName}.city.invalidNull
 *       {objectName}.city.invalidMaxLength
 *       {objectName}.tag.invalidMaxLength
 *       {objectName}.state.invalidNull
 *       {objectName}.state.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class RestaurantValidator extends AbstractValidator<Restaurant> {

	// length
	private static final int MAX_NAME_LENGTH = 256;
	private static final int MAX_CITY_LENGTH = 256;
	private static final int MAX_STATE_LENGTH = 256;
	private static final int MAX_TAG_LENGTH = 256;
	private static final int MAX_NOTES_LENGTH = 25000;

	// keys
	private static final String INVALID_UNIQUE = "invalidUnique";

	// properties
	private Restaurants restaurants;

	// constructor
	public RestaurantValidator(String objectName, Restaurant restaurant, Restaurants restaurants) {
		super(objectName, restaurant);
		this.restaurants = restaurants;
	}

	@Override
	protected void validate(String objectName, Restaurant restaurant, List<ValidationFailure> failures) {
		String nameName = join(objectName, Restaurant.NAME);
		String cityName = join(objectName, Restaurant.CITY);
		String stateName = join(objectName, Restaurant.STATE);
		String tagName = join(objectName, Restaurant.TAG);
		String notesName = join(objectName, Restaurant.NOTES);

		// add all validation failures for a null restaurant
		if(restaurant == null)
		{
			addValidationResult(NotNullValidator.invalidNull(nameName));
			addValidationResult(NotNullValidator.invalidNull(cityName));
			addValidationResult(NotNullValidator.invalidNull(stateName));
			return;
		}

		// validate the restaurant
		if(restaurants.hasDuplicate(restaurant))
			failures.add(new ValidationFailure(join(objectName, INVALID_UNIQUE), nameName, cityName, stateName));

		addValidationResult(new NotNullValidator(nameName, restaurant.getName()));
		addValidationResult(new NotNullValidator(cityName, restaurant.getCity()));
		addValidationResult(new NotNullValidator(stateName, restaurant.getState()));
		addValidationResult(new MaxLengthValidator(nameName, restaurant.getName(), MAX_NAME_LENGTH));
		addValidationResult(new MaxLengthValidator(tagName, restaurant.getTag(), MAX_TAG_LENGTH));
		addValidationResult(new MaxLengthValidator(cityName, restaurant.getCity(), MAX_CITY_LENGTH));
		addValidationResult(new MaxLengthValidator(stateName, restaurant.getState(), MAX_STATE_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, restaurant.getNotes(), MAX_NOTES_LENGTH));
	}

}