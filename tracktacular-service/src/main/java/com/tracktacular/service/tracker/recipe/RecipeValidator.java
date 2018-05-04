package com.tracktacular.service.tracker.recipe;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * RecipeValidator
 *
 * Ensures that the recipe is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.name.invalidNull
 *       {objectName}.name.invalidMaxLength
 *       {objectName}.ingredients.invalidNull
 *       {objectName}.ingredients.invalidMaxLength
 *       {objectName}.directions.invalidNull
 *       {objectName}.directions.invalidMaxLength
 *       {objectName}.description.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *       {objectName}.recipeTagsAsString.invalidMaxLength
 *       {objectName}.recipeTagsAsString.invalidNameMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class RecipeValidator extends AbstractValidator<Recipe> {

	// length
	private static final int MAX_NAME_LENGTH = 256;
	private static final int MAX_DESCRIPTION_LENGTH = 256;
	private static final int MAX_INGREDIENTS_LENGTH = 25000;
	private static final int MAX_DIRECTIONS_LENGTH = 25000;
	private static final int MAX_NOTES_LENGTH = 25000;
	private static final int MAX_RECIPE_TAG_NAME_LENGTH = 35;
	private static final int MAX_RECIPE_TAGS_AS_STRING_LENGTH = 1024;

	// keys
	private static final String INVALID_NAME_MAX_LENGTH = "invalidNameMaxLength";

	// constructor
	public RecipeValidator(String objectName, Recipe recipe) {
		super(objectName, recipe);
	}

	@Override
	protected void validate(String objectName, Recipe recipe, List<ValidationFailure> failures) {
		String nameName = join(objectName, Recipe.NAME);
		String ingredientsName = join(objectName, Recipe.INGREDIENTS);
		String directionsName = join(objectName, Recipe.DIRECTIONS);
		String descriptionName = join(objectName, Recipe.DESCRIPTION);
		String notesName = join(objectName, Recipe.NOTES);
		String recipeTagsAsStringName = join(objectName, "recipeTagsAsString");

		// add all validation failures for a null recipe
		if(recipe == null)
		{
			addValidationResult(NotNullValidator.invalidNull(nameName));
			addValidationResult(NotNullValidator.invalidNull(ingredientsName));
			addValidationResult(NotNullValidator.invalidNull(directionsName));
			return;
		}

		// validate the recipe
		addValidationResult(new NotNullValidator(nameName, recipe.getName()));
		addValidationResult(new MaxLengthValidator(nameName, recipe.getName(), MAX_NAME_LENGTH));

		addValidationResult(new NotNullValidator(ingredientsName, recipe.getIngredients()));
		addValidationResult(new MaxLengthValidator(ingredientsName, recipe.getIngredients(), MAX_INGREDIENTS_LENGTH));

		addValidationResult(new NotNullValidator(directionsName, recipe.getDirections()));
		addValidationResult(new MaxLengthValidator(directionsName, recipe.getDirections(), MAX_DIRECTIONS_LENGTH));

		addValidationResult(new MaxLengthValidator(descriptionName, recipe.getDescription(), MAX_DESCRIPTION_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, recipe.getNotes(), MAX_NOTES_LENGTH));

		// validate the tags
		boolean validRecipeTagsAsStringLength = addValidationResult(
				new MaxLengthValidator(recipeTagsAsStringName, recipe.getRecipeTagsAsString(), MAX_RECIPE_TAGS_AS_STRING_LENGTH));

		if(validRecipeTagsAsStringLength && recipe.getTags() != null)
			for(RecipeTag recipeTag : recipe.getTags())
				if(recipeTag.getName().length() > MAX_RECIPE_TAG_NAME_LENGTH)
					failures.add(new ValidationFailure(join(recipeTagsAsStringName, INVALID_NAME_MAX_LENGTH),
							new Object[] {MAX_RECIPE_TAG_NAME_LENGTH}, recipeTagsAsStringName));
	}

}