package com.tracktacular.web.page.tracker.recipe;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.recipe.Recipe;
import com.tracktacular.service.tracker.recipe.RecipeBook;
import com.tracktacular.service.tracker.recipe.RecipeChapter;
import com.tracktacular.service.tracker.recipe.RecipeTrackerFacade;
import com.tracktacular.service.tracker.recipe.RecipeTrackerPreferences;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * RecipeTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class RecipeTrackerActionBean extends TrackerActionBean<RecipeTrackerFacade, RecipeTrackerPreferences> {

	// static properties
	protected static final int TAG_CLOUD_COUNT = 50;
	protected static final double TAG_CLOUD_MIN_WEIGHT = 3;
	protected static final double TAG_CLOUD_MAX_WEIGHT = 14;

	// properties
	private Recipe recipe;
	private RecipeChapter recipeChapter;
	private RecipeBook recipeBook;

	@Override
	public Tracker getTracker() {
		return Tracker.RECIPE;
	}

	/**
	 * Retrieves the recipeBook for a user.
	 *
	 * @return RecipeBook
	 */
	public RecipeBook getRecipeBook() {
		if(recipeBook == null)
			recipeBook = getTrackerFacade().getRecipeBook(getTrackerUser());

		return recipeBook;
	}

	/**
	 * Save or updates a Recipe.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveRecipe() {
		Recipe recipe = getRecipe();
		populatePersistableUser(recipe);
		getTrackerFacade().saveRecipe(recipe);

		if(recipe.isActiveState())
			getContext().addSuccessMessage("recipe.saveRecipe.success");

		else if(recipe.isDeletedState())
			getContext().addSuccessMessage("recipe.deleteRecipe.success", recipe.getName());

		return getAjaxSourceResolution();
	}

	/**
	 * Toggles the favorite property.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution toggleRecipeFavorite() {
		Recipe recipe = getRecipe();
		recipe.setFavorite(!recipe.isFavorite());
		getTrackerFacade().saveRecipe(recipe);
		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Recipe.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteRecipe() {
		Recipe recipe = getRecipe();
		getTrackerFacade().deleteRecipe(recipe);
		getContext().addSuccessMessage("recipe.deleteRecipe.success", recipe.getName());
		return getAjaxSourceResolution();
	}

	// getters and setters
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public void setRecipeChapter(RecipeChapter recipeChapter) {
		this.recipeChapter = recipeChapter;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public RecipeChapter getRecipeChapter() {
		return recipeChapter;
	}

}