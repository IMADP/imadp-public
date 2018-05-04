package com.tracktacular.web.page.tracker.recipe;

import java.util.List;

import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.service.tracker.recipe.Recipe;
import com.tracktacular.service.tracker.recipe.RecipeChapter;
import com.tracktacular.web.IgnoreInPublicMode;


/**
 * RecipeBookActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-recipe/recipe-book/chapter-{selectedRecipeChapter=all}")
public class RecipeBookActionBean extends RecipeTrackerActionBean {
	private String sortedChapters;
	private String sortedRecipes;
	private String selectedRecipeChapter;

	/**
	 * Save or updates a Chapter.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveRecipeChapter() {
		RecipeChapter recipeChapter = getRecipeChapter();
		populatePersistableUser(recipeChapter);
		getTrackerFacade().saveRecipeChapter(recipeChapter);
		getContext().addSuccessMessage("recipe.saveRecipeChapter.success", recipeChapter.getTitle());
		return getAjaxSourceResolution();
	}

	/**
	 * Sorts Chapters.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution sortRecipeChapters() {
		List<RecipeChapter> sortedChaptersList = convertObjectList(sortedChapters, RecipeChapter.class);
		List<RecipeChapter> recipeChapters = getResortedList(sortedChaptersList);
		getTrackerFacade().saveRecipeChapters(recipeChapters);
		getContext().addSuccessMessage("recipe.sortRecipeChapters.success");
		return getAjaxSourceResolution();
	}

	/**
	 * Toggles the Chapter collapsed property.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution toggleRecipeChapterCollapse() {
		RecipeChapter recipeChapter = getRecipeChapter();
		recipeChapter.setCollapsed(!recipeChapter.isCollapsed());
		getTrackerFacade().saveRecipeChapter(recipeChapter);
		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Chapter.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteRecipeChapter() {
		RecipeChapter recipeChapter = getRecipeChapter();
		getTrackerFacade().deleteRecipeChapter(recipeChapter);
		getContext().addSuccessMessage("recipe.deleteRecipeChapter.success", recipeChapter.getTitle());
		return getAjaxSourceResolution();
	}

	/**
	 * Sorts Recipes.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution sortRecipes() {
		List<Recipe> sortedRecipesList = convertObjectList(sortedRecipes, Recipe.class);
		List<Recipe> recipes = getResortedList(sortedRecipesList);
		getTrackerFacade().saveRecipes(recipes);
		getContext().addSuccessMessage("recipe.sortRecipes.success");
		return getAjaxSourceResolution();
	}

	// setters
	public void setSortedChapters(String sortedChapters) {
		this.sortedChapters = sortedChapters;
	}

	public String getSelectedRecipeChapter() {
		return selectedRecipeChapter;
	}

	public void setSelectedRecipeChapter(String selectedRecipeChapter) {
		this.selectedRecipeChapter = selectedRecipeChapter;
	}

	public String getSortedChapters() {
		return sortedChapters;
	}

	public String getSortedRecipes() {
		return sortedRecipes;
	}

	public void setSortedRecipes(String sortedRecipes) {
		this.sortedRecipes = sortedRecipes;
	}

}