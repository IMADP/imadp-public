package com.tracktacular.service.tracker.recipe;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.tag.TagCloud;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * ITracktacularRecipeTrackerFacade
 *
 * Provides functionality for recipe tracking.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface RecipeTrackerFacade extends TrackerFacade {

    /**
	 * Gets a Recipe by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return <T> recipe or <code>null</code> if no recipe was found.
	 */
	public Recipe getRecipe(User user, String uid);

	/**
	 * Saves or updates a Recipe.
	 *
	 * @param recipe
	 */
	public void saveRecipe(Recipe recipe);

	/**
	 * Saves or updates a List of Recipes.
	 *
	 * @param recipes
	 */
	public void saveRecipes(List<Recipe> recipes);

	/**
	 * Removes a Recipe, without completing it.
	 *
	 * @param recipe
	 */
	public void deleteRecipe(Recipe recipe);

	/**
	 * Finds a List of deleted Recipes for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Recipe>
	 */
	public List<Recipe> findDeletedRecipes(User user, CriteriaParams<Recipe> criteriaParams);

	/**
	 * Finds the count of all deleted Recipes for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedRecipeCount(User user);

	/**
	 * Gets a RecipeChapter by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return <T> chapter or <code>null</code> if no chapter was found.
	 */
	public RecipeChapter getRecipeChapter(User user, String uid);

	/**
	 * Saves a Chapter.
	 *
	 * @param chapter
	 */
	public void saveRecipeChapter(RecipeChapter chapter);

	/**
	 * Saves or updates a List of Chapters.
	 *
	 * @param chapters
	 */
	public void saveRecipeChapters(List<RecipeChapter> chapters);

	/**
	 * Removes a Chapter.
	 *
	 * @param chapter
	 */
	public void deleteRecipeChapter(RecipeChapter chapter);

	/**
	 * Returns the recipe book for a user.
	 *
	 * @param user
	 * @return RecipeBook
	 */
	public RecipeBook getRecipeBook(User user);

	/**
	 * Finds a List of RecipeTag for a user, name, and criteriaParams.
	 *
	 * @param user
	 * @param name
	 * @param criteriaParams
	 * @return List<RecipeTag>
	 */
	public List<RecipeTag> findRecipeTags(User user, String name, CriteriaParams<RecipeTag> criteriaParams);

	/**
	 * Finds the count of all active RecipeTags for a user
	 *
	 * @param user
	 * @param name
	 * @return long
	 */
	public long findRecipeTagCount(User user, String name);

	/**
	 * Finds a tagCloud for a user.
	 *
	 * @param user
	 * @param tagCount
	 * @param minWeight
	 * @param maxWeight
	 * @return TagCloud
	 */
	public TagCloud findRecipeTagCloud(User user, int tagCount, double minWeight, double maxWeight);

}