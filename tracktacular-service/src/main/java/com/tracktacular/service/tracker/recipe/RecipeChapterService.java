package com.tracktacular.service.tracker.recipe;

import java.util.List;

import com.imadp.service.user.PersistableUserService;

/**
 * RecipeChapterService
 *
 * Provides common retrieval operations for RecipeChapter objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface RecipeChapterService extends PersistableUserService<RecipeChapter> {

	/**
	 * Returns true if a chapter is in use for the given user, false otherwise.
	 *
	 * @param chapter
	 * @return boolean
	 */
	public boolean isTitleInUse(RecipeChapter chapter);

	/**
	 * Saves a recipe.
	 *
	 * @param recipe
	 */
	public void saveRecipe(Recipe recipe);

	/**
	 * Saves a List of recipes.
	 *
	 * @param recipes
	 */
	public void saveRecipes(List<Recipe> recipes);

	/**
	 * Deletes a recipe.
	 *
	 * @param recipe
	 */
	public void deleteRecipe(Recipe recipe);

}