package com.tracktacular.service.tracker.restaurant;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * MealValidator
 *
 * Ensures that the meal is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.name.invalidNull
 *       {objectName}.name.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *       {objectName}.tag.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class MealValidator extends AbstractValidator<Meal> {

	// length
	private static final int MAX_NAME_LENGTH = 256;
	private static final int MAX_NOTES_LENGTH = 25000;
	private static final int MAX_TAG_LENGTH = 256;

	// constructor
	public MealValidator(String objectName, Meal meal) {
		super(objectName, meal);
	}

	@Override
	protected void validate(String objectName, Meal meal, List<ValidationFailure> failures) {
		String nameName = join(objectName, Meal.NAME);
		String notesName = join(objectName, Meal.NOTES);
		String tagName = join(objectName, Meal.TAG);

		// add all validation failures for a null meal
		if(meal == null)
		{
			addValidationResult(NotNullValidator.invalidNull(nameName));
			return;
		}

		// validate the journal
		addValidationResult(new NotNullValidator(nameName, meal.getName()));
		addValidationResult(new MaxLengthValidator(tagName, meal.getTag(), MAX_TAG_LENGTH));
		addValidationResult(new MaxLengthValidator(nameName, meal.getName(), MAX_NAME_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, meal.getNotes(), MAX_NOTES_LENGTH));
	}

}